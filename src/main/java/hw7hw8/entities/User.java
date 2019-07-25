package hw7hw8.entities;

import com.epam.jdi.tools.DataClass;
import lombok.Getter;
import org.testng.Assert;

import java.io.IOException;
import java.util.Properties;

import static hw3.utils.FileUtils.readPropertiesFromFile;

@Getter
public class User extends DataClass<User> {
    public String userName;
    public String password;
    public String fullName;

    public static User readPredefinedUser() throws IOException {
        Properties properties = readPropertiesFromFile("src/test/resources/properties/user.properties");
        return new User().set(user -> {
            user.userName = properties.getProperty("user.name");
            user.password = properties.getProperty("user.password");
            user.fullName = properties.getProperty("user.user.name");
        });
    }
}