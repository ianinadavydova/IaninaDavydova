package hw4.enums;

public enum SummaryEven {
    TWO(2),
    FOUR(4),
    SIX(6),
    EIGHT(8);

    private final int value;

    SummaryEven(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override public String toString() {
        return Integer.toString(value);
    }
}
