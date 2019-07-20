package hw7hw8;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.ui.html.common.Icon;
import com.epam.jdi.light.ui.html.common.Label;
import com.epam.jdi.light.ui.html.complex.Menu;
import hw7hw8.entities.User;
import hw7hw8.forms.LoginForm;
import org.hamcrest.Matchers;

@Url("/index.html") @Title("Home Page")
public class HomePage extends WebPage {
    @Css("#login-form") public static LoginForm loginForm;
    @Css("img#user-icon") public static Icon userIcon;
    @Css("#user-name") public static Label fullName;
    @Css(".navbar-nav.m-l8 > li") public static Menu header;

    public void checkLoggedin(User user) {
        fullName.shouldBe().text(Matchers.equalTo(user.getFullName()));
    }
}
