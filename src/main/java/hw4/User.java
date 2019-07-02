package hw4;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Properties;

@Builder
@Getter
@ToString
public class User {
    private String userName;
    private String password;
    private String name;

    public static User createFromProperties(Properties properties) {
        return User.builder()
                .userName(properties.getProperty("user.name"))
                .password(properties.getProperty("user.password"))
                .name(properties.getProperty("user.user.name"))
                .build();
    }
}
