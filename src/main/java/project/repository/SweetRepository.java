package project.repository;

import project.domain.sweet.AbstractSweet;

import java.util.List;

public interface SweetRepository extends CRUDRepository<AbstractSweet> {
    List<AbstractSweet>findByName(String name);
    List<AbstractSweet> findBySugarContentRange(Byte start, Byte end);
    List<AbstractSweet> findByPriceRange(Integer start, Integer end);
    List<AbstractSweet> findByWeightRange(Integer start, Integer end);
}

