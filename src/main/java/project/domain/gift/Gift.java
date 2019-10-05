package project.domain.gift;

import project.domain.sweet.AbstractSweet;
import project.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gift {
    private static Long counter = 0L;

    private final Long id;
    private Integer weight;
    private Long price;
    private final User owner;
    private List<AbstractSweet> sweets;

    public Gift(User owner) {
        if (owner == null) {
            throw new IllegalArgumentException("User has not been found");
        }

        this.id = ++counter;
        this.owner = owner;
        this.weight = 0;
        this.price = 0L;
        this.sweets = new ArrayList<>();
    }

    public Gift(User owner, List<AbstractSweet> sweets) {
        this(owner);
        if (sweets == null) {
            throw new IllegalArgumentException("There are no sweets");
        }
        this.sweets = sweets;

        for (AbstractSweet sweet : sweets) {
            this.weight += sweet.getWeight();
            this.price += sweet.getPrice();
        }
    }

    public static Long getCounter() {
        return counter;
    }

    public Long getId() {
        return id;
    }

    public Integer getWeight() {
        return weight;
    }

    public Long getPrice() {
        return price;
    }

    public User getOwner() {
        return owner;
    }

    public List<AbstractSweet> getSweets() {
        return sweets;
    }

    public void addSweet(AbstractSweet sweet) {
        if (sweet == null) {
            throw new IllegalArgumentException("Can`t add empty sweet");
        }

        this.sweets.add(sweet);
        this.weight += sweet.getWeight();
        this.price += sweet.getPrice();
    }

    public void removeSweet(AbstractSweet sweet) {
        if (sweet == null) {
            throw new IllegalArgumentException("Can`t remove empty sweet");
        }

        this.sweets.remove(sweet);
        this.weight -= sweet.getWeight();
        this.price -= sweet.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Gift gift = (Gift) o;

        return Objects.equals(id, gift.id) &&
                Objects.equals(weight, gift.weight) &&
                Objects.equals(owner, gift.owner) &&
                Objects.equals(sweets, gift.sweets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weight, owner, sweets);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Gift № ").append(id).append(", ").
                append("owner: ").append(owner.getName()).append(" ").append(owner.getSurname()).append(", ").
                append("weight: ").append(weight).append("g").append(", ").
                append("price: ").append(price).append("$\n");
        for (AbstractSweet sweet : sweets) {
            stringBuilder.append(sweet).append('\n');
        }

        return stringBuilder.toString();
    }
}
