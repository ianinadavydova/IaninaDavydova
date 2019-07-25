package hw7;

import hw3.enums.Color;
import hw3.enums.Metal;
import hw7hw8.entities.MetalsColors;
import hw7hw8.entities.User;

import java.io.IOException;
import java.util.Arrays;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "dataSet")
    Object[][] dataSet() throws IOException {
        return new Object[][]{
                {
                        User.readPredefinedUser(),
                        new MetalsColors().set(entity -> {
                            entity.summaryOdd = "3";
                            entity.summaryEven = "8";
                            entity.elements = Arrays.asList("Water", "Fire");
                            entity.colors = Color.RED.getText();
                            entity.metals = Metal.SELEN.getName();
                            entity.vegetables = Arrays.asList("Cucumber", "Tomato");
                        }),
                        Arrays.asList(
                                "Summary: 11",
                                "Elements: Water, Fire",
                                "Color: Red",
                                "Metal: Selen",
                                "Vegetables: Cucumber, Tomato"
                        )
                },
        };
    }
}