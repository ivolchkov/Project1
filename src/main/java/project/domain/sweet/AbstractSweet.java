package project.domain.sweet;

import java.util.Objects;

public abstract class AbstractSweet {
    private final String name;
    private final Integer weight;
    private final Byte sugarContent;

    public AbstractSweet(String name, Integer weight, Byte sugarContent) {
        this.name = name;
        this.weight = weight;
        this.sugarContent = sugarContent;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public Byte getSugarContent() {
        return sugarContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractSweet that = (AbstractSweet) o;

        return Objects.equals(weight, that.weight) &&
                Objects.equals(sugarContent, that.sugarContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, sugarContent);
    }
}
