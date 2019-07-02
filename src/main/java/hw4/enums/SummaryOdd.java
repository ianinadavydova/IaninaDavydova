package hw4.enums;

public enum SummaryOdd {
    ONE(1),
    THREE(3),
    FIVE(5),
    SEVEN(7);

    private final int value;

    SummaryOdd(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override public String toString() {
        return Integer.toString(value);
    }
}
