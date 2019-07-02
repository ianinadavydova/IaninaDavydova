package hw4;

import java.util.Properties;

public class User {
    private String userName;
    private String password;
    private String name;

    public User(Properties userProperties) {
        userName = userProperties.getProperty("user.name");
        password = userProperties.getProperty("user.password");
        name = userProperties.getProperty("user.user.name");
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
