package project.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.domain.sweet.AbstractSweet;
import project.repository.SweetRepository;

import java.util.*;

@Service
public class SweetServiceImpl implements SweetService {
    private static final Logger LOGGER = Logger.getLogger("file");

    private final SweetRepository repository;

    @Autowired
    public SweetServiceImpl(SweetRepository repository) {
        this.repository = repository;
    }

    @Override
    public AbstractSweet addSweet(AbstractSweet sweet) {
        if ( sweet == null ) {
            LOGGER.warn("Sweet is empty!");
            throw new IllegalArgumentException("Sweet is empty!");
        }
        return repository.save(sweet);
    }

    @Override
    public AbstractSweet showSweetById(Long id) {
        if ( id == null ) {
            LOGGER.warn("Invalid id!");
            throw new IllegalArgumentException("Invalid id!");
        }
        return repository.findById(id).<RuntimeException>orElseThrow(()-> {
            LOGGER.warn("There is no sweet by this id!");
            throw new IllegalArgumentException("There is no sweet by this id!");
        });
    }
    //Test
    @Override
    public List<AbstractSweet> showAllSweets() {
        List<AbstractSweet> sweets = repository.findAll();

        return sweets.isEmpty() ? Collections.emptyList() : sweets;
    }

    @Override
    public List<AbstractSweet> getSweetsByOrder(Map<Long, Integer> order) {
        if ( order == null ) {
            LOGGER.warn("You have an empty order!");
            throw new IllegalArgumentException("You have an empty order!");
        }
        List<AbstractSweet> sweets = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : order.entrySet()) {
            Optional<AbstractSweet> sweet = repository.findById(entry.getKey());

            if (sweet.isPresent()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    sweets.add(sweet.get());
                }
            }
        }

        return sweets;
    }
}
