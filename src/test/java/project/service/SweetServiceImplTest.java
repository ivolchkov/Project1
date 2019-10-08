package project.service;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.domain.sweet.AbstractSweet;
import project.domain.sweet.Tartalet;
import project.domain.sweet.choice.TartaletFilling;
import project.repository.SweetRepository;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SweetServiceImplTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private SweetRepository repository;

    @InjectMocks
    private SweetServiceImpl service;

    @After
    public void resetMock() {
        reset(repository);
    }

    @Test
    public void shouldAddSweetToRep() {
        AbstractSweet expected = new Tartalet(TartaletFilling.CHERRY);
        when(repository.save(any(AbstractSweet.class))).thenReturn(expected);
        AbstractSweet actual = service.addSweet(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAddNullSweet() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Sweet is empty!");

        service.addSweet(null);
    }

    @Test
    public void shouldReturnSweetById() {
        AbstractSweet expected = new Tartalet(TartaletFilling.CHERRY);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(expected));
        AbstractSweet actual = service.showSweetById(1L);

        assertEquals(expected,actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalid id!");

        service.showSweetById(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenThereIsNoSweetByThisId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("There is no sweet by this id!");
        when(repository.findById(any(Long.class))).thenReturn(Optional.empty());

        service.showSweetById(3L);
    }

    @Test
    public void shouldReturnSweetsByOrder() {
        AbstractSweet sweet = new Tartalet(TartaletFilling.CHERRY);
        List<AbstractSweet> expected = Arrays.asList(sweet, sweet);
        Map<Long,Integer> order = new HashMap<>();
        order.put(1L, 1);
        order.put(2L, 1);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(sweet));
        List<AbstractSweet> actual = service.getSweetsByOrder(order);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenOrderIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("You have an empty order!");

        service.getSweetsByOrder(null);
    }

    @Test
    public void shouldReturnAllSweet() {
        AbstractSweet sweet = new Tartalet(TartaletFilling.CHERRY);
        List<AbstractSweet> expected = Arrays.asList(sweet, sweet);
        when(repository.findAll()).thenReturn(expected);
        List<AbstractSweet> actual = service.showAllSweets();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        List<AbstractSweet> expected = Collections.emptyList();
        when(repository.findAll()).thenReturn(expected);
        List<AbstractSweet> actual = service.showAllSweets();

        assertEquals(expected, actual);
    }

}