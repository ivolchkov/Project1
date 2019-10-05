package project.domain.sweet;

import project.domain.sweet.choice.SweetFilling;

import java.util.Objects;

public class ChocolateSweet extends Chocolate {
    private static final String NAME = "Chocolate sweet";
    private static final Long PRICE = 1L;
    private static final Integer WEIGHT = 30;
    private static final Byte SUGAR_CONTENT = 40;

    private final SweetFilling filling;

    public ChocolateSweet(SweetFilling filling) {
        super(NAME, PRICE, WEIGHT, SUGAR_CONTENT);
        this.filling = filling;
    }

    public SweetFilling getFilling() {
        return filling;
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

        ChocolateSweet that = (ChocolateSweet) o;

        return filling == that.filling;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filling);
    }

    @Override
    public String toString() {
        return super.toString() + ", sweet filling: " + filling.getDescription();
    }
}
