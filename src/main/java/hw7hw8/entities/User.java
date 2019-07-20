package hw7hw8.entities;

import com.epam.jdi.tools.DataClass;
import lombok.Getter;

@Getter
public class User extends DataClass<User> {
    public String userName;
    public String password;
    public String fullName;
}