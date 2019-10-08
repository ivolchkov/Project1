package project.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.domain.gift.Gift;
import project.domain.sweet.AbstractSweet;
import project.domain.user.User;
import project.repository.GiftRepository;
import project.service.exception.InvalidRangeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GiftServiceImpl implements GiftService {
    private static final Logger LOGGER = Logger.getLogger("file");

    private final GiftRepository repository;

    @Autowired
    public GiftServiceImpl(GiftRepository repository) {
        this.repository = repository;
    }

    //Test
    @Override
    public Gift addGift(Gift gift) {
        if ( gift == null ) {
            LOGGER.warn("Gift is empty!");
            throw new IllegalArgumentException("Gift is empty!");
        }
        return repository.save(gift);
    }

    @Override
    public Gift showGiftByOwner(User owner) {
        validateUser(owner);

        return repository.findByOwner(owner).<RuntimeException>orElseThrow(() -> {
            LOGGER.warn("This user has not gift!");
            throw new IllegalArgumentException("This user has not gift!");
        });
    }

    @Override
    public List<AbstractSweet> sortSweetsBySugarContent(User owner) {
        validateUser(owner);

        Optional<Gift> gift = repository.findByOwner(owner);
        if (gift.isPresent()) {
            List<AbstractSweet> sweets = new ArrayList<>(gift.get().getSweets());
            sweets.sort(AbstractSweet.COMPARE_BY_SUGAR_CONTENT);
            return sweets;
        }

        LOGGER.warn("There is no gift by this user");
        throw new IllegalArgumentException("There is no gift by this user");
    }

    @Override
    public List<AbstractSweet> sortSweetsByPrice(User owner) {
        validateUser(owner);

        Optional<Gift> gift = repository.findByOwner(owner);
        if (gift.isPresent()) {
            List<AbstractSweet> sweets = new ArrayList<>(gift.get().getSweets());
            sweets.sort(AbstractSweet.COMPARE_BY_PRICE);
            return sweets;
        }

        LOGGER.warn("There is no gift by this user");
        throw new IllegalArgumentException("There is no gift by this user");
    }

    @Override
    public List<AbstractSweet> sortSweetsByWeight(User owner) {
        validateUser(owner);

        Optional<Gift> gift = repository.findByOwner(owner);
        if (gift.isPresent()) {
            List<AbstractSweet> sweets = new ArrayList<>(gift.get().getSweets());
            sweets.sort(AbstractSweet.COMPARE_BY_WEIGHT);
            return sweets;
        }

        LOGGER.warn("There is no gift by this user");
        throw new IllegalArgumentException("There is no gift by this user");
    }

    @Override
    public List<AbstractSweet> showSweetsBySugarContentRange(User owner, Byte start, Byte end) {
        validateUser(owner);

        if (start == null || end == null) {
            LOGGER.warn("You have empty values in range!");
            throw new InvalidRangeException("You have empty values in range!");
        }
        if (start >= end) {
            LOGGER.warn("You chose invalid range!");
            throw new InvalidRangeException("You chose invalid range!");
        }

        Optional<Gift> gift = repository.findByOwner(owner);
        if (gift.isPresent()) {
            final List<AbstractSweet> suitableSweets = new ArrayList<>();

            for (AbstractSweet sweet : gift.get().getSweets()) {
                if (sweet.getSugarContent() >= start && sweet.getSugarContent() < end) {
                    suitableSweets.add(sweet);
                }
            }
            return suitableSweets;
        }

        LOGGER.warn("There is no gift by this user");
        throw new IllegalArgumentException("There is no gift by this user");
    }

    @Override
    public void addSweet(User owner, AbstractSweet sweet) {
        validateUser(owner);

        Optional<Gift> gift = repository.findByOwner(owner);
        if (gift.isPresent()) {
            gift.get().addSweet(sweet);
        } else {
            LOGGER.warn("There is no gift by this user");
            throw new IllegalArgumentException("There is no gift by this user");
        }
    }

    @Override
    public void removeSweet(User owner, AbstractSweet sweet) {
        validateUser(owner);

        Optional<Gift> gift = repository.findByOwner(owner);
        if (gift.isPresent()) {
            gift.get().removeSweet(sweet);
        } else {
            LOGGER.warn("There is no gift by this user");
            throw new IllegalArgumentException("There is no gift by this user");
        }
    }

    @Override
    public Gift deleteByOwner(User owner) {
        return repository.deleteByOwner(owner).<RuntimeException>orElseThrow(()-> {
            LOGGER.warn("This user has not gift!");
            throw new IllegalArgumentException("This user has not gift!");
        });
    }

    private void validateUser(User owner) {
        if (owner == null) {
            LOGGER.warn("Invalid user!");
            throw new IllegalArgumentException("Invalid user!");
        }
    }
}
