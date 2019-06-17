package hw1;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "long input")
    public static Object [][] longInput() {
        return new Object[][] {{Long.MIN_VALUE, Long.MAX_VALUE}, {Long.MIN_VALUE, Long.MIN_VALUE},
                {Long.MAX_VALUE, Long.MAX_VALUE}, {-1, 0}, {-1, 1}, {1, 1}};
    }

    @DataProvider(name = "double input")
    public static Object [][] doubleInput() {
        return new Object[][] {{Double.MIN_VALUE, Double.MAX_VALUE}, {Double.MIN_VALUE, Double.MIN_VALUE},
                {Double.MAX_VALUE, Double.MAX_VALUE}, {-0.1, 0}, {-0.1, 0.1}, {0.1, 0.1}};
    }
}
