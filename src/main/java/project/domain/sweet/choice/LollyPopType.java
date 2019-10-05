package project.domain.sweet.choice;

public enum LollyPopType {
    CIRCLE("Circle"), HEART("Heart"), ROOSTER("Rooster");

    String description;

    LollyPopType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
