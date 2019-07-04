package hw4.ex2;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import hw3.enums.Color;
import hw3.enums.Element;
import hw3.enums.Metal;
import hw4.BaseTestHw4;
import hw4.HomePage;
import hw4.MetalAndColorsPage;
import hw4.enums.Result;
import hw4.enums.Vegetable;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;

public class Exercise2 extends BaseTestHw4 {
    @Test(dataProviderClass = DataProvider.class, dataProvider = "dataSet")
    public void Exercise2Test(DataSample sample) {
        // Step #1 Open test site by URL, test site is opened
        // Step #2 Assert Browser title
        // Step #3 Perform login
        HomePage homePage = getHomePage();

        // Step #4 Click on the link on the Header section
        homePage.getMetalsAndColors().click();
        checkMetalAndColorsPage(Selenide.page(MetalAndColorsPage.class), sample);
    }

    private static void checkMetalAndColorsPage(MetalAndColorsPage page, DataSample sample) {
        // Step #6 Fill form on the page
        fillForm(page, sample);

        // Step #7 Click “Submit” button
        page.getSubmit().click();

        // Step #8 Check Results block output on the right-side
        checkResults(page.getResults(), sample);
    }

    private static void fillForm(MetalAndColorsPage page, DataSample sample) {
        fillSummary(page, sample.getSummary());
        fillElements(page.getElements(), sample.getElements());
        fillColor(page, sample.getColor());
        fillMetal(page, sample.getMetal());
        fillVegetables(page, sample.getVegetables());
    }

    private static void fillSummary(MetalAndColorsPage page, DataSample.Summary summary) {
        if (summary == null)
            return;

        selectRadioButton(page.getSummaryOdd(), summary.getOdd());
        selectRadioButton(page.getSummaryEven(), summary.getEven());
    }

    private static void fillElements(ElementsCollection checkBoxes, Element[] elements) {
        if (elements == null)
            return;

        for (Element element : elements) {
            checkBoxes.findBy(exactText(element.getName())).click();
        }
    }

    private static void fillColor(MetalAndColorsPage page, Color value) {
        if (value == null)
            return;

        selectOption(page.getColorsButton(), page.getColorOptions(), value.getText());
    }

    private static void fillMetal(MetalAndColorsPage page, Metal value) {
        if (value == null)
            return;

        selectOption(page.getMetalsButton(), page.getMetalOptions(), value.getName());
    }

    private static void fillVegetables(MetalAndColorsPage page, Vegetable[] vegetables) {
        if (vegetables == null)
            return;

        page.getVegetablesButton().click();
        ElementsCollection options = page.getVegetableOptions();
        for (SelenideElement option : options) {
            boolean shouldBeSelected = Arrays.stream(vegetables).anyMatch(v -> v.getName().equals(option.getText()));
            if (option.find(By.tagName("input")).isSelected() != shouldBeSelected) {
                option.click();
            }
        }
    }

    private static <E> void selectRadioButton(ElementsCollection radioButton, E e) {
        radioButton.findBy(exactText(e.toString())).click();
    }

    private static void selectOption(SelenideElement button, ElementsCollection options, String value) {
        button.click();
        options.findBy(exactText(value)).click();
    }

    private static void checkResults(ElementsCollection results, DataSample expected) {
        checkSummary(findResult(results, Result.SUMMARY), expected.getSummary());
        checkElements(findResult(results, Result.ELEMENTS), expected.getElements());
        checkColor(findResult(results, Result.COLOR), expected.getColor());
        checkMetal(findResult(results, Result.METAL), expected.getMetal());
        checkVegetables(findResult(results, Result.VEGETABLES), expected.getVegetables());
    }

    private static void checkSummary(SelenideElement actualSummary, DataSample.Summary expected) {
        if (expected != null) {
            int sum = expected.getEven().getValue() + expected.getOdd().getValue();
            actualSummary.shouldHave(text(String.format("%s: %d", Result.SUMMARY.getPrefix(), sum)));
        }
    }

    private static void checkElements(SelenideElement actual, Element[] expected) {
        if (expected != null) {
            String stringized = Arrays.stream(expected).map(Element::getName).collect(Collectors.joining(", "));
            checkText(actual, Result.ELEMENTS, stringized);
        }
    }

    private static void checkColor(SelenideElement actual, Color expected) {
        if (expected != null) {
            checkText(actual, Result.COLOR, expected.getText());
        }
    }

    private static void checkMetal(SelenideElement actual, Metal expected) {
        if (expected != null) {
            checkText(actual, Result.METAL, expected.getName());
        }
    }

    private static void checkVegetables(SelenideElement actual, Vegetable[] expected) {
        if (expected != null) {
            String stringized = Arrays.stream(expected).map(Vegetable::getName).collect(Collectors.joining(", "));
            checkText(actual, Result.VEGETABLES, stringized);
        }
    }

    private static void checkText(SelenideElement actual, Result resultKind, String expected) {
        actual.shouldHave(text(String.format("%s: %s", resultKind.getPrefix(), expected)));
    }

    private static SelenideElement findResult(ElementsCollection results, Result resultKind) {
        return results.findBy(Condition.cssClass(resultKind.getCssClass()));
    }
}