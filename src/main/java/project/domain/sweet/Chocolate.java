package project.domain.sweet;


import project.domain.sweet.choice.SweetType;

import static project.domain.sweet.choice.SweetType.CHOCOLATE;

public abstract class Chocolate extends AbstractSweet {
    private static final SweetType TYPE = CHOCOLATE;

    public Chocolate(String name, Integer weight, Byte sugarContent) {
        super(name, weight, sugarContent);
    }

    public SweetType getType() {
        return TYPE;
    }

}
