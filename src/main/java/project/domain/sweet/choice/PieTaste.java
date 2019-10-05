package project.domain.sweet.choice;

public enum PieTaste {
    APPLE("Apple"), BERRY("Berry"), PEACH("Peach");

    String description;

    PieTaste(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
