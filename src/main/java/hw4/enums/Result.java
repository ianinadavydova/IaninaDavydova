package hw4.enums;

public enum Result {
    SUMMARY("Summary", "summ-res"),
    ELEMENTS("Elements", "elem-res"),
    COLOR("Color", "col-res"),
    METAL("Metal", "met-res"),
    VEGETABLES("Vegetables", "sal-res");

    private final String prefix;
    private final String cssClass;

    Result(String prefix, String clazz) {
        this.prefix = prefix;
        this.cssClass = clazz;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getCssClass() {
        return cssClass;
    }
}
