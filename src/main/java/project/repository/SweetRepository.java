package project.repository;

import java.util.List;

public interface SweetRepository<T> extends Repository<T> {
    List<T>findByName(String name);
    List<T> findBySugarContentRange(Byte start, Byte end);
    List<T> findByPriceRange(Integer start, Integer end);
    List<T> findByWeightRange(Integer start, Integer end);
}

