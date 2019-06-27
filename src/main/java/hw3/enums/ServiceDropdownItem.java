package hw3.enums;

public enum ServiceDropdownItem {
    Support("SUPPORT"),
    Dates("DATES"),
    ComplexTable("COMPLEX TABLE"),
    SimpleTable("SIMPLE TABLE"),
    TablesWithPage("TABLE WITH PAGES"),
    DifferentElements("DIFFERENT ELEMENTS");

    private final String text;

    ServiceDropdownItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
