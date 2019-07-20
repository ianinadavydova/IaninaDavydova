package hw7hw8;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import hw7hw8.forms.MetalsColorsForm;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@Url("/metals-colors.html") @Title("Metal and Colors")
public class MetalsColorsPage extends WebPage {
    @Css(".form") public static MetalsColorsForm metalsColorsForm;

    @Css(".info-panel-body-result li")
    public static List<WebElement> resultSection;

    public List<String> getLog() {
        return resultSection.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
