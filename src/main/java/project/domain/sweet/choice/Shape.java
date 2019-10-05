package project.domain.sweet.choice;

public enum Shape {
    CIRCLE("Circle"), HEART("Heart"), ROOSTER("Rooster");

    String description;

    Shape(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
