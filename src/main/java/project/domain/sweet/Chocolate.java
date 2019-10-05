package project.domain.sweet;


import project.domain.sweet.choice.SweetType;

import static project.domain.sweet.choice.SweetType.CARAMEL;
import static project.domain.sweet.choice.SweetType.CHOCOLATE;

public abstract class Chocolate extends AbstractSweet {
    private static final SweetType TYPE = CHOCOLATE;

    public Chocolate(String name, Long price, Integer weight, Byte sugarContent) {
        super(name, price, weight, sugarContent);
    }

    public SweetType getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return super.toString() + ", sweet type: " + CHOCOLATE.getDescription();
    }
}
