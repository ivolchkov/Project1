package project.service;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.gift.Gift;
import project.domain.sweet.AbstractSweet;
import project.domain.sweet.ChocolateBar;
import project.domain.sweet.Tartalet;
import project.domain.sweet.choice.ChocolateBarType;
import project.domain.sweet.choice.TartaletFilling;
import project.domain.user.User;
import project.repository.GiftRepository;
import project.service.exception.InvalidRangeException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GiftServiceImplTest {
    private User user;
    private List<AbstractSweet> sweets;
    private Gift gift;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private GiftRepository repository;

    @InjectMocks
    private GiftServiceImpl service;

    @Before
    public void initProperties() {
        AbstractSweet tartalet = new Tartalet(TartaletFilling.CHERRY);
        AbstractSweet bar = new ChocolateBar(ChocolateBarType.MILK);
        sweets = Arrays.asList(tartalet, bar);
        user = new User.UserBuilder().withName("User").build();
        gift = new Gift(user, sweets);
    }

    @After
    public void resetMock() {
        reset(repository);
    }

    @Test
    public void shouldShowGiftByOwner() {
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.of(gift));
        Gift actual = service.showGiftByOwner(user);

        assertEquals(gift, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUserIsNullShowingGiftByOwner() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid user!");

        service.showGiftByOwner(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGiftIsNotFound() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("This user has not gift!");
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.empty());

        service.showGiftByOwner(user);
    }

    @Test
    public void shouldSortSweetsBySugarContent() {
        Gift test = new Gift(user,Arrays.asList(sweets.get(1),sweets.get(0)));
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.of(test));
        List<AbstractSweet> actual = service.sortSweetsBySugarContent(user);

        assertEquals(sweets, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUserIsNullSortingSweetsBySugarCont() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid user!");

        service.sortSweetsBySugarContent(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGiftIsNotFoundSortingSweetsBySugCon() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("There is no gift by this user");
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.empty());

        service.sortSweetsBySugarContent(user);
    }

    @Test
    public void shouldSortSweetsByPrice() {
        List<AbstractSweet> expected = Arrays.asList(sweets.get(1), sweets.get(0));
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.of(gift));
        List<AbstractSweet> actual = service.sortSweetsByPrice(user);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUserIsNullSortingByPrice() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid user!");

        service.sortSweetsByPrice(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGiftIsNotFoundSortingSweetsByPrice() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("There is no gift by this user");
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.empty());

        service.sortSweetsByPrice(user);
    }

    @Test
    public void shouldSortSweetsByWeight() {
        List<AbstractSweet> expected = Arrays.asList(sweets.get(1), sweets.get(0));
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.of(gift));
        List<AbstractSweet> actual = service.sortSweetsByWeight(user);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUserIsNullSortingByWeight() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid user!");

        service.sortSweetsByWeight(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGiftIsNotFoundSortingSweetsByWeight() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("There is no gift by this user");
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.empty());

        service.sortSweetsByWeight(user);
    }

    @Test
    public void shouldReturnSweetsBySugarContentRange() {
        Byte start = 15;
        Byte end = 45;
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.of(gift));
        List<AbstractSweet> actual = service.showSweetsBySugarContentRange(user, start, end);

        assertEquals(sweets, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUserIsNullSortingBySugarRange() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid user!");

        service.showSweetsBySugarContentRange(null, null, null);
    }

    @Test
    public void shouldThrowInvalidRangeExceptionWhenStartIsNullSortingBySugarRange() {
        exception.expect(InvalidRangeException.class);
        exception.expectMessage("You have empty values in range!");
        Byte end = 45;

        service.showSweetsBySugarContentRange(user, null, end);
    }

    @Test
    public void shouldThrowInvalidRangeExceptionWhenEndIsNullSortingBySugarRange() {
        exception.expect(InvalidRangeException.class);
        exception.expectMessage("You have empty values in range!");
        Byte start = 15;

        service.showSweetsBySugarContentRange(user, start, null);
    }

    @Test
    public void shouldThrowInvalidRangeExceptionWhenStartIsGreaterThanEndSortingBySugarRange() {
        exception.expect(InvalidRangeException.class);
        exception.expectMessage("You chose invalid range!");
        Byte start = 15;
        Byte end = 5;

        service.showSweetsBySugarContentRange(user, start, end);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGiftIsNotFoundShowSweetsBySugarRange() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("There is no gift by this user");
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.empty());
        Byte start = 15;
        Byte end = 50;

        service.showSweetsBySugarContentRange(user, start, end);
    }

    @Test
    public void shouldAddSweet() {
        AbstractSweet tartalet = new Tartalet(TartaletFilling.CHERRY);
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.of(gift));
        service.addSweet(user, tartalet);
        Integer expected = 3;
        Integer actual = gift.getSweets().size();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUserIsNullAddingSweet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid user!");

        service.addSweet(null, null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGiftIsNotFoundAddingSweet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("There is no gift by this user");
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.empty());

        service.addSweet(user,null);
    }

    @Test
    public void shouldRemoveSweet() {
        AbstractSweet tartalet = new Tartalet(TartaletFilling.CHERRY);
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.of(gift));
        service.removeSweet(user, tartalet);
        Integer expected = 1;
        Integer actual = gift.getSweets().size();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenUserIsNullRemovingSweet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid user!");

        service.removeSweet(null, null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenGiftIsNotFoundRemovingSweet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("There is no gift by this user");
        when(repository.findByOwner(any(User.class))).thenReturn(Optional.empty());

        service.removeSweet(user,null);
    }

    @Test
    public void shouldDeleteGiftByOwner() {
        when(repository.deleteByOwner(any(User.class))).thenReturn(Optional.of(gift));
        Gift actual = service.deleteByOwner(user);

        assertEquals(gift, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionDeletingGiftByOwner() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("This user has not gift!");
        when(repository.deleteByOwner(any(User.class))).thenReturn(Optional.empty());
        service.deleteByOwner(user);
    }

}