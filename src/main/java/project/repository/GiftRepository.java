package project.repository;


import project.domain.sweet.AbstractSweet;
import project.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface GiftRepository<T> extends Repository<T> {
    Optional<T> findByOwner(User owner);
    List<AbstractSweet> findSweetsBySugarContent();
    List<AbstractSweet> findSweetsByPrice();
    List<AbstractSweet> findSweetsByWeight();
    List<AbstractSweet> findSweetsBySugarContentRange(Byte start, Byte end);

    void removeSweet(AbstractSweet sweet);
    void addSweet(AbstractSweet sweet);

    Optional<T> deleteByOwner(User owner);
}
