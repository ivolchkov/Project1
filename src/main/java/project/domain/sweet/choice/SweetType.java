package project.domain.sweet.choice;

public enum SweetType {
    CARAMEL("Caramel"), CHOCOLATE("Chocolate"), BAKERY("Bakery");

    String description;

    SweetType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
