package hw4.enums;

public enum Page {
    HOME("Home Page"),
    DIFFERENT_ELEMENTS("Different Elements");

    private final String title;

    Page(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

