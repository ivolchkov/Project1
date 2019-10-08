package project.domain.gift;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import project.domain.sweet.AbstractSweet;
import project.domain.sweet.Tartalet;
import project.domain.sweet.choice.TartaletFilling;
import project.domain.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GiftTest {
    private AbstractSweet sweet;
    private Gift gift;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        User user = new User.UserBuilder().withName("Ihor").build();
        sweet = new Tartalet(TartaletFilling.CHERRY);
        List<AbstractSweet> sweets = new ArrayList<>();
        sweets.add(sweet);
        sweets.add(sweet);
        gift = new Gift(user, sweets);
    }

    @Test
    public void shouldAddSweet() {
        Integer expected = gift.getWeight() + sweet.getWeight();
        gift.addSweet(sweet);
        Integer actual = gift.getWeight();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddNullSweet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Can`t add empty sweet");

        gift.addSweet(null);
    }

    @Test
    public void shouldRemoveSweet() {
        Integer expected = gift.getWeight() - sweet.getWeight();
        gift.removeSweet(sweet);
        Integer actual = gift.getWeight();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenRemoveNullSweet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Can`t remove empty sweet");

        gift.removeSweet(null);
    }
}