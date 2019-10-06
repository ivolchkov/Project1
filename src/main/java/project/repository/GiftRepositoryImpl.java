package project.repository;

import project.domain.gift.Gift;
import project.domain.sweet.AbstractSweet;
import project.domain.user.User;

import java.util.*;

public class GiftRepositoryImpl implements GiftRepository<Gift> {
    private final Map<Long,Gift> idToGift = new HashMap<>();

    @Override
    public Gift save(Gift gift) {
        return idToGift.put(gift.getId(),gift);
    }

    @Override
    public Optional<Gift> findById(Long id) {
        return Optional.ofNullable(idToGift.get(id));
    }

    @Override
    public List<Gift> findAll() {
        return new ArrayList<>(idToGift.values());
    }

    @Override
    public Optional<Gift> findByOwner(User owner) {
        Optional<Gift> gift = Optional.empty();

        for (Gift gft: idToGift.values()) {
            if ( Objects.equals(gft.getOwner(), owner) ) {
                gift = Optional.of(gft);

                return gift;
            }
        }

        return gift;
    }

    @Override
    public List<AbstractSweet> findSweetsBySugarContent(Long id) {
        List<AbstractSweet> sweets = idToGift.get(id).getSweets();

        sweets.sort(AbstractSweet.COMPARE_BY_SUGAR_CONTENT);

        return sweets;
    }

    @Override
    public List<AbstractSweet> findSweetsByPrice(Long id) {
        List<AbstractSweet> sweets = idToGift.get(id).getSweets();

        sweets.sort(AbstractSweet.COMPARE_BY_PRICE);

        return sweets;
    }

    @Override
    public List<AbstractSweet> findSweetsByWeight(Long id) {
        List<AbstractSweet> sweets = idToGift.get(id).getSweets();

        sweets.sort(AbstractSweet.COMPARE_BY_WEIGHT);

        return sweets;
    }

    @Override
    public List<AbstractSweet> findSweetsBySugarContentRange(Long id, Byte start, Byte end) {
        List<AbstractSweet> suitableSweets = new ArrayList<>();
        List<AbstractSweet> sweets = idToGift.get(id).getSweets();

        for (AbstractSweet sweet: sweets) {
            if ( sweet.getSugarContent() >= start && sweet.getSugarContent() < end ) {
                suitableSweets.add(sweet);
            }
        }

        return suitableSweets;
    }

    @Override
    public void update(Gift gift) {
        idToGift.put(gift.getId(),gift);
    }

    @Override
    public void removeSweet(Long id, AbstractSweet sweet) {
        Gift gift = idToGift.get(id);
        gift.removeSweet(sweet);
    }

    @Override
    public void addSweet(Long id, AbstractSweet sweet) {
        Gift gift = idToGift.get(id);
        gift.addSweet(sweet);
    }

    @Override
    public Optional<Gift> deleteById(Long id) {
        return Optional.ofNullable(idToGift.remove(id));
    }

    @Override
    public Optional<Gift> deleteByOwner(User owner) {
        for (Gift gift: idToGift.values()) {
            if ( Objects.equals(gift.getOwner(), owner) )  {
                return Optional.of(idToGift.remove(gift.getId()));
            }
        }

        return Optional.empty();
    }
}
