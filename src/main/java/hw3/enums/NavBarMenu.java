package hw3.enums;

public enum NavBarMenu {

    HOME("Home"),
    CONTACT_FORM("Contact form"),
    SERVICE("Service"),
    METALSCOLORS("Metals & Colors");

    private final String name;

    NavBarMenu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}