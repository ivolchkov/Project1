package project.domain.sweet;


import project.domain.sweet.choice.SweetType;

import static project.domain.sweet.choice.SweetType.CARAMEL;
import static project.domain.sweet.choice.SweetType.CHOCOLATE;

public abstract class Chocolate extends AbstractSweet {
    private static final SweetType TYPE = CHOCOLATE;

    public Chocolate(String name, Integer price, Integer weight, Byte sugarContent) {
        super(name, price, weight, sugarContent);
    }

    public SweetType getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", sweet type: %-9s", CHOCOLATE.getDescription());
    }
}
