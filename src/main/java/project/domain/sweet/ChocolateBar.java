package project.domain.sweet;

import project.domain.sweet.choice.ChocolateBarType;

import java.util.Objects;

public class ChocolateBar extends Chocolate {
    private static final String NAME = "Chocolate bar";
    private static final Integer WEIGHT = 100;
    private static final Byte SUGAR_CONTENT = 35;

    private final ChocolateBarType chocolateType;

    public ChocolateBar(ChocolateBarType type) {
        super(NAME, WEIGHT, SUGAR_CONTENT);
        this.chocolateType = type;
    }

    public ChocolateBarType getChocolateType() {
        return chocolateType;
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

        ChocolateBar that = (ChocolateBar) o;

        return chocolateType == that.chocolateType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chocolateType);
    }
}
