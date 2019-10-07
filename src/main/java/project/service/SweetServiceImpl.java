package project.service;

import org.apache.log4j.Logger;
import project.domain.sweet.AbstractSweet;
import project.repository.SweetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SweetServiceImpl implements SweetService {
    private static final Logger LOGGER = Logger.getLogger("file");

    private final SweetRepository repository;

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
        return repository.findById(id).orElseThrow(()-> {
            LOGGER.warn("There is no sweet by this id!");
            throw new IllegalArgumentException("There is no sweet by this id!");
        });
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
