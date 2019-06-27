package hw3.enums;

public enum CheckBox {
    WATER("Water"),
    EARTH("Earth"),
    WIND("Wind"),
    FIRE("Fire");

    private final String name;

    CheckBox(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
