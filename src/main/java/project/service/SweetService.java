package project.service;

import project.domain.sweet.AbstractSweet;

import java.util.List;
import java.util.Map;

public interface SweetService {
    AbstractSweet addSweet(AbstractSweet sweet);

    AbstractSweet showSweetById(Long id);
    List<AbstractSweet> showAllSweets();
    List<AbstractSweet> getSweetsByOrder(Map<Long,Integer> order);
}
