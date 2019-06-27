package hw3.enums;

public enum BaseEnum {
    HOME_PAGE_TITLE("Home Page");

    private String PageTitle;

    BaseEnum(String PageTitle) {
        this.PageTitle = PageTitle;
    }

    public String getPageTitle() {
        return PageTitle;
    }
}
