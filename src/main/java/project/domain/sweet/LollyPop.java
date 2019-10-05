package project.domain.sweet;

import project.domain.sweet.choice.LollyPopType;

import java.util.Objects;

public class LollyPop extends Caramel {
    private static final String NAME = "Lolly pop";
    private static final Integer WEIGHT = 70;
    private static final Byte SUGAR_CONTENT = 80;

    private final LollyPopType lollyPopType;

    public LollyPop(LollyPopType type) {
        super(NAME, WEIGHT, SUGAR_CONTENT);
        this.lollyPopType= type;
    }

    public LollyPopType getLollyPopType() {
        return lollyPopType;
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

        return lollyPopType== lollyPop.lollyPopType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lollyPopType);
    }
}
