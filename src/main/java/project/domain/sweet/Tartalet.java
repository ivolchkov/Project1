package project.domain.sweet;

import project.domain.sweet.choice.TartaletFilling;

import java.util.Objects;

public class Tartalet extends Bakery {
    private static final String NAME = "Tartalet";
    private static final Long PRICE = 5L;
    private static final Integer WEIGHT = 200;
    private static final Byte SUGAR_CONTENT = 20;

    private final TartaletFilling filling;

    public Tartalet(TartaletFilling filling) {
        super(NAME, PRICE, WEIGHT, SUGAR_CONTENT);
        this.filling = filling;
    }

    public TartaletFilling getFilling() {
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

        Tartalet tartalet = (Tartalet) o;

        return filling == tartalet.filling;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filling);
    }

    @Override
    public String toString() {
        return super.toString() + ", tartalet filling: " + filling.getDescription();
    }
}
