package hw3.enums;

public enum Element {
    WATER("Water"),
    EARTH("Earth"),
    WIND("Wind"),
    FIRE("Fire");

    private final String name;

    Element(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
