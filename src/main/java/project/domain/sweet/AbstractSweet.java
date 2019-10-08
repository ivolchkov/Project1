package project.domain.sweet;

import java.util.Comparator;
import java.util.Objects;

public abstract class AbstractSweet {
    public static final Comparator<AbstractSweet> COMPARE_BY_NAME = (current,other) -> current.name.compareTo(other.name);
    public static final Comparator<AbstractSweet> COMPARE_BY_PRICE = (current,other) -> current.price - other.price;
    public static final Comparator<AbstractSweet> COMPARE_BY_WEIGHT = (current,other) -> current.weight - other.weight;
    public static final Comparator<AbstractSweet> COMPARE_BY_SUGAR_CONTENT = (current,other) -> current.sugarContent - other.sugarContent;

    private static Long counter = 0L;

    private final Long id;
    private final String name;
    private final Integer price;
    private final Integer weight;
    private final Byte sugarContent;

    public AbstractSweet(String name, Integer price, Integer weight, Byte sugarContent) {
        this.id = ++counter;
        this.name = name;
        this.price = price;
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

    public Integer getPrice() {
        return price;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Sweet № ").append(String.format("%-2d, ", id)).
                append(String.format("name: %-15s, ", name)).
                append(String.format("weight: %-3dg, ", weight)).
                append(String.format("sugar content: %-2d%%, ", sugarContent)).
                append(String.format("price: %-2d$", price));

        return stringBuilder.toString();
    }

    }
