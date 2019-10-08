package project.domain.sweet;

import project.domain.sweet.choice.SweetType;

import static project.domain.sweet.choice.SweetType.*;

public abstract class Caramel extends AbstractSweet {
    private static final SweetType TYPE = CARAMEL;

    public Caramel(String name, Integer price, Integer weight, Byte sugarContent) {
        super(name, price, weight, sugarContent);
    }

    public SweetType getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", sweet type: %-9s", CARAMEL.getDescription());
    }
}
