package project.repository;


import project.domain.sweet.AbstractSweet;
import project.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface GiftRepository<T> extends Repository<T> {
    Optional<T> findByOwner(User owner);
    List<AbstractSweet> findSweetsBySugarContent(Long id);
    List<AbstractSweet> findSweetsByPrice(Long id);
    List<AbstractSweet> findSweetsByWeight(Long id);
    List<AbstractSweet> findSweetsBySugarContentRange(Long id, Byte start, Byte end);

    void removeSweet(Long id, AbstractSweet sweet);
    void addSweet(Long id, AbstractSweet sweet);

    Optional<T> deleteByOwner(User owner);
}
