package hw3.enums;

public enum RadioButton {
    GOLD("Gold"),
    SILVER("Silver"),
    BRONZE("Bronze"),
    SELEN("Selen");

    private final String name;

    RadioButton(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
