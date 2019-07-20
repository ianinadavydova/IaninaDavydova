package hw7hw8.forms;

import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.light.ui.html.common.TextField;
import hw7hw8.entities.User;

public class LoginForm extends Form<User> {
    @Css("#name")
    TextField userName;

    @Css("#password")
    TextField password;

    @Css("[type=submit]")
    Button enter;
}
