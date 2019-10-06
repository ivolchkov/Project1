package project.service;

import project.domain.gift.Gift;
import project.domain.sweet.AbstractSweet;
import project.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface GiftService {
    Optional<Gift> showGiftByOwner(User owner);
    List<AbstractSweet> sortSweetsBySugarContent(Long id);
    List<AbstractSweet> sortSweetsByPrice(Long id);
    List<AbstractSweet> sortSweetsByWeight(Long id);
    List<AbstractSweet> showSweetsBySugarContentRange(Long id, Byte start, Byte end);

    void removeSweet(Long id, AbstractSweet sweet);
    void addSweet(Long id, AbstractSweet sweet);

    Optional<Gift> deleteByOwner(User owner);
}
