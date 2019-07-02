package hw3.enums;

public enum Color {
    YELLOW("Yellow"),
    GREEN("Green"),
    BLUE("Blue");

    private final String text;

    Color(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
