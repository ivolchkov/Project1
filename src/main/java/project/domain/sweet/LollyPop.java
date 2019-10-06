package project.domain.sweet;

import project.domain.sweet.choice.Shape;

import java.util.Objects;

public class LollyPop extends Caramel {
    private static final String NAME = "Lolly pop";
    private static final Integer PRICE = 2;
    private static final Integer WEIGHT = 70;
    private static final Byte SUGAR_CONTENT = 80;

    private final Shape shape;

    public LollyPop(Shape type) {
        super(NAME, PRICE, WEIGHT, SUGAR_CONTENT);
        this.shape = type;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        LollyPop lollyPop = (LollyPop) o;

        return shape == lollyPop.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shape);
    }

    @Override
    public String toString() {
        return super.toString() + ", lolly pop shape: " + shape.getDescription();
    }
}
