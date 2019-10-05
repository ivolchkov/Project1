package project.domain.sweet;

import java.util.Objects;

public abstract class AbstractSweet {
    private static Long counter = 0L;

    private final Long id;
    private final String name;
    private final Integer weight;
    private final Byte sugarContent;

    public AbstractSweet(String name, Integer weight, Byte sugarContent) {
        this.id = ++counter;
        this.name = name;
        this.weight = weight;
        this.sugarContent = sugarContent;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
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
