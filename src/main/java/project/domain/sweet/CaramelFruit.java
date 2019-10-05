package project.domain.sweet;

import project.domain.sweet.choice.Fruit;

import java.util.Objects;

public class CaramelFruit extends Caramel {
    private static final String NAME = "Caramel fruit";
    private static final Integer WEIGHT = 80;
    private static final Byte SUGAR_CONTENT = 30;

    private final Fruit fruit;

    public CaramelFruit(Fruit fruit) {
        super(NAME, WEIGHT, SUGAR_CONTENT);
        this.fruit = fruit;
    }

    public Fruit getFruit() {
        return fruit;
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

        CaramelFruit that = (CaramelFruit) o;

        return fruit == that.fruit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fruit);
    }
}
