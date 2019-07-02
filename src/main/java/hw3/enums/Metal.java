package hw3.enums;

public enum Metal {
    GOLD("Gold"),
    SILVER("Silver"),
    BRONZE("Bronze"),
    SELEN("Selen");

    private final String name;

    Metal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
