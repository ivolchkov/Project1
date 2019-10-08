package project.service;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.user.User;
import project.repository.UserRepository;
import project.service.encoder.PasswordEncoder;
import project.service.exception.AlreadyRegisteredException;
import project.service.exception.InvalidRegistrationException;
import project.service.exception.StudentNotFoundException;
import project.service.validator.UserValidator;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private UserRepository repository;
    @Mock
    private UserValidator validator;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userService;

    @After
    public void resetMock() {
        reset(repository);
        reset(validator);
        reset(encoder);
    }

    @Test
    public void shouldRegisterStudent() {
        User expected = new User.UserBuilder().withName("Igor").
                withSurname("Volchkov").
                withPassword("Babushka3529").
                withEmail("igorik@gmail.com").
                build();
        when(repository.save(any(User.class))).thenReturn(expected);
        when(encoder.encode(any(String.class))).thenReturn(Optional.of(expected.getPassword()));
        User actual = userService.register(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenRegisterStudent() {
        User user = new User.UserBuilder().
                withEmail("test@gmail.com").
                build();
        exception.expect(AlreadyRegisteredException.class);
        exception.expectMessage("User is already registered by this e-mail");

        when(repository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(user));


        userService.register(user);
    }

    @Test
    public void shouldThrowInvalidRegistrationExceptionWhenRegisterNullStudent() {
        exception.expect(InvalidRegistrationException.class);

        doThrow(InvalidRegistrationException.class).when(validator).validate(null);
        userService.register(null);
    }

    @Test
    public void shouldLoginStudent() {
        User expected = new User.UserBuilder().
                withPassword("Babushka3529").
                withEmail("igorik@gmail.com").
                build();
        when(repository.findByEmail("igorik@gmail.com")).thenReturn(Optional.of(expected));
        when(encoder.encode("Babushka3529")).thenReturn(Optional.of(expected.getPassword()));

        User actual = userService.login("igorik@gmail.com", "Babushka3529");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowStudentNotFoundExceptionWithIncorrectPassword() {
        exception.expect(StudentNotFoundException.class);
        exception.expectMessage("Incorrect password");
        User expected = new User.UserBuilder().
                withPassword("Babushka3529").
                withEmail("igorik@gmail.com").
                build();
        when(encoder.encode(any(String.class))).thenReturn(Optional.of("test"));
        when(repository.findByEmail("igorik@gmail.com")).thenReturn(Optional.ofNullable(expected));

        userService.login("igorik@gmail.com", "test");
    }

    @Test
    public void shouldThrowStudentNotFoundExceptionWithIncorrectEmail() {
        exception.expect(StudentNotFoundException.class);
        exception.expectMessage("There is no user with this e-mail");
        when(encoder.encode(any(String.class))).thenReturn(Optional.of("test"));
        when(repository.findByEmail(any(String.class))).thenReturn(Optional.empty());

        userService.login("igorik@gmail.com", "test");
    }
}
