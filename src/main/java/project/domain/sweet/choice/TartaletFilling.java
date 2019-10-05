package project.domain.sweet.choice;

public enum TartaletFilling {
    RASPBERRY("Raspberry"), STRAWBERRY("Strawberry"), CHERRY("Cherry");

    String description;

    TartaletFilling(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
