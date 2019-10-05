package project.domain.sweet.choice;

public enum SweetFilling {
    NUTS("Nuts"), BUTTER_CREAM("Butter cream"), CANDIED_FRUIT("Candied fruit");

    String description;

    SweetFilling(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
