package hw1;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Calculator calc;

    @BeforeMethod
    public void createCalc() {
        calc = new Calculator();
    }
}
