package hw7hw8.forms;

import com.epam.jdi.light.elements.complex.Droplist;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.UI;
import com.epam.jdi.light.ui.html.base.HtmlRadioGroup;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.complex.*;
import hw7hw8.entities.MetalsColors;

public class MetalsColorsForm extends Form<MetalsColors> {

    @Css("#odds-selector p")
    RadioButtons summaryOdd;

    @Css("#even-selector p")
    RadioButtons summaryEven;

    @JDropdown(root = "div[ui=dropdown]",
            value = ".filter-option",
            list = "li",
            expand = ".caret")
    Droplist colors;

    @Css("#elements-checklist p")
    private HtmlRadioGroup elementCheckBoxes;

    @JDropdown(root = "div[ui=combobox]",
            value = ".filter-option",
            list = "li",
            expand = ".caret")
    Droplist metals;

    @JDropdown(root = "#salad-dropdown",
            value = ".dropdown-toggle",
            list = "li",
            expand = ".caret")
    private Droplist vegetablesDropdown;

    @UI("#submit-button")
    Button submit;

    @Override
    public void submit(MetalsColors entity) {
        entity.elements.forEach(option -> elementCheckBoxes.select(option));
        vegetablesDropdown.select(vegetablesDropdown.getSelected());
        entity.vegetables.forEach(option -> vegetablesDropdown.select(option));
        super.submit(entity);
    }
}
