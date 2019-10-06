package project.service;

import project.domain.sweet.AbstractSweet;

import java.util.List;
import java.util.Map;

public interface SweetService {
    AbstractSweet addSweet(AbstractSweet sweet);

    AbstractSweet showSweetById(Long id);
//    List<AbstractSweet> showSweetsBySugarContentRange(Byte start, Byte end);
//    List<AbstractSweet> showSweetsByPriceRange(Integer start, Integer end);
//    List<AbstractSweet> showSweetsByWeightRange(Integer start, Integer end);
    List<AbstractSweet> getSweetsByOrder(Map<Long,Integer> order);
}
