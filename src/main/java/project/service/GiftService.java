package project.service;

import project.domain.gift.Gift;
import project.domain.sweet.AbstractSweet;
import project.domain.user.User;

import java.util.List;


public interface GiftService {
    Gift addGift(Gift gift);

    Gift showGiftByOwner(User owner);
    List<AbstractSweet> sortSweetsBySugarContent(User owner);
    List<AbstractSweet> sortSweetsByPrice(User owner);
    List<AbstractSweet> sortSweetsByWeight(User owner);
    List<AbstractSweet> showSweetsBySugarContentRange(User owner, Byte start, Byte end);

    void removeSweet(User owner, AbstractSweet sweet);
    void addSweet(User owner, AbstractSweet sweet);

    Gift deleteByOwner(User owner);
}
