package project.domain.sweet.choice;

public enum Fruit {
    APPLE("Apple"), MELON("Melon"), PEAR("Pear"), BANANA("Banana");

    String description;

    Fruit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
