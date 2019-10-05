package project.domain.sweet.choice;

public enum ChocolateBarType {
    BLACK("Black"), WHITE("White"), MILK("Milk");

    String description;

    ChocolateBarType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
