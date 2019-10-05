package project.domain.sweet;

import project.domain.sweet.choice.SweetType;

import static project.domain.sweet.choice.SweetType.*;

public abstract class Caramel extends AbstractSweet {
    private static final SweetType TYPE = CARAMEL;

    public Caramel(String name, Integer weight, Byte sugarContent) {
        super(name, weight, sugarContent);
    }

    public SweetType getType() {
        return TYPE;
    }
}
