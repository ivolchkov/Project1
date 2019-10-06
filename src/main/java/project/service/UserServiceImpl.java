package project.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.domain.user.User;
import project.repository.UserRepository;
import project.service.encoder.PasswordEncoder;
import project.service.exception.AlreadyRegisteredException;
import project.service.exception.InvalidEncodingException;
import project.service.exception.StudentNotFoundException;
import project.service.validator.UserValidator;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger("file");

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserValidator validator;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder, UserValidator validator) {
        this.repository = repository;
        this.encoder = encoder;
        this.validator = validator;
    }

    @Override
    public User register(User user) {
        validator.validate(user);

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("User is already registered by this e-mail");
            throw new AlreadyRegisteredException("User is already registered by this e-mail");
        }

        String encoded = encoder.encode(user.getPassword()).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        User userWithEncodedPass = new User(user, encoded);

        return repository.save(userWithEncodedPass);
    }

    @Override
    public User login(String email, String password) {
        String encodedPassword = encoder.encode(password).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        final Optional<User> user = repository.findByEmail(email);

        if (!user.isPresent()) {
            LOGGER.warn("There is no user with this e-mail");
            throw new StudentNotFoundException("There is no user with this e-mail");
        } else {
            if (user.get().getPassword().equals(encodedPassword)) {
                return user.get();
            } else {
                LOGGER.warn("Incorrect password");
                throw new StudentNotFoundException("Incorrect password");
            }
        }
    }
}
