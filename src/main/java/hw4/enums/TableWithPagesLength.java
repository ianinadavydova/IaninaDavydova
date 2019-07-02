package hw4.enums;

public enum TableWithPagesLength {
    DEFAULT(5),
    TEN(10);

    private final int value;

    TableWithPagesLength(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
