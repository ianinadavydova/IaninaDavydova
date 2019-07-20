package hw3.enums;

public enum Color {
    YELLOW("Yellow"),
    GREEN("Green"),
    RED("Red"),
    BLUE("Blue");

    private final String text;

    Color(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
