package hw4.enums;

public enum Vegetable {
    CUCUMBER("Cucumber"),
    TOMATO("Tomato"),
    VEGETABLES("Vegetables"),
    ONION("Onion");

    private final String name;

    Vegetable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
