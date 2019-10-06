package project.domain.sweet;

import project.domain.sweet.choice.PieTaste;

import java.util.Objects;

public class Pie extends Bakery {
    private static final String NAME = "Pie";
    private static final Integer PRICE = 30;
    private static final Integer WEIGHT = 800;
    private static final Byte SUGAR_CONTENT = 30;

    private final PieTaste taste;

    public Pie(PieTaste taste) {
        super(NAME, PRICE, WEIGHT, SUGAR_CONTENT);
        this.taste = taste;
    }

    public PieTaste getTaste() {
        return taste;
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

        Pie pie = (Pie) o;

        return taste == pie.taste;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taste);
    }

    @Override
    public String toString() {
        return super.toString() + ", pie taste: " + taste.getDescription();
    }
}
