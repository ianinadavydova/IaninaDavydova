package hw3.enums;

public enum Color {
    YELLOW("Yellow");

    private final String text;

    Color(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
