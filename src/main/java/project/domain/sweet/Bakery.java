package project.domain.sweet;

import project.domain.sweet.choice.SweetType;

import static project.domain.sweet.choice.SweetType.BAKERY;

public abstract class Bakery extends AbstractSweet {
    private static final SweetType TYPE = BAKERY;

    public Bakery(String name, Integer weight, Byte sugarContent) {
        super(name, weight, sugarContent);
    }

    public static SweetType getTYPE() {
        return TYPE;
    }
}
