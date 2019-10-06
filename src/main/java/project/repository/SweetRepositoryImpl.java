package project.repository;

import project.domain.sweet.AbstractSweet;


import java.util.*;

public class SweetRepositoryImpl implements SweetRepository<AbstractSweet> {
    private final Map<Long,AbstractSweet> idToSweet = new HashMap<>();

    @Override
    public AbstractSweet save(AbstractSweet sweet) {
        return idToSweet.put(sweet.getId(), sweet);
    }

    @Override
    public Optional<AbstractSweet> findById(Long id) {
        return Optional.ofNullable(idToSweet.get(id));
    }

    @Override
    public List<AbstractSweet> findAll() {
        return new ArrayList<>(idToSweet.values());
    }

    @Override
    public void update(AbstractSweet sweet) {
        idToSweet.put(sweet.getId(), sweet);
    }

    @Override
    public Optional<AbstractSweet> deleteById(Long id) {
        return Optional.ofNullable(idToSweet.remove(id));
    }

    @Override
    public List<AbstractSweet> findByName(String name) {
        List<AbstractSweet> suitableSweets = new ArrayList<>();

        for (AbstractSweet sweet: idToSweet.values()) {
            if ( Objects.equals(sweet.getName(), name) ) {
                suitableSweets.add(sweet);
            }
        }

        return suitableSweets;
    }

    @Override
    public List<AbstractSweet> findBySugarContentRange(Byte start, Byte end) {
        List<AbstractSweet> suitableSweets = new ArrayList<>();

        for (AbstractSweet sweet: idToSweet.values()) {
            if ( sweet.getSugarContent() >= start && sweet.getSugarContent() < end ) {
                suitableSweets.add(sweet);
            }
        }

        return suitableSweets;
    }

    @Override
    public List<AbstractSweet> findByPriceRange(Integer start, Integer end) {
        List<AbstractSweet> suitableSweets = new ArrayList<>();

        for (AbstractSweet sweet: idToSweet.values()) {
            if ( sweet.getPrice() >= start && sweet.getPrice() < end ) {
                suitableSweets.add(sweet);
            }
        }

        return suitableSweets;
    }

    @Override
    public List<AbstractSweet> findByWeightRange(Integer start, Integer end) {
        List<AbstractSweet> suitableSweets = new ArrayList<>();

        for (AbstractSweet sweet: idToSweet.values()) {
            if ( sweet.getWeight() >= start && sweet.getWeight() < end ) {
                suitableSweets.add(sweet);
            }
        }

        return suitableSweets;
    }
}
