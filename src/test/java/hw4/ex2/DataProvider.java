package hw4.ex2;

import hw3.enums.Color;
import hw3.enums.Element;
import hw3.enums.Metal;
import hw4.enums.SummaryEven;
import hw4.enums.SummaryOdd;
import hw4.enums.Vegetable;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "dataSet")
    Object[][] dataSet() {
        return new Object[][]{
                {
                        DataSample.builder()
                                .elements(new Element[]{Element.EARTH})
                                .color(Color.YELLOW)
                                .metal(Metal.GOLD)
                                .build()
                },
                {
                        DataSample.builder()
                                .summary(new DataSample.Summary(SummaryOdd.THREE, SummaryEven.EIGHT))
                                .vegetables(new Vegetable[]{Vegetable.CUCUMBER, Vegetable.TOMATO})
                                .build()
                },
                {
                        DataSample.builder()
                                .summary(new DataSample.Summary(SummaryOdd.THREE, SummaryEven.TWO))
                                .elements(new Element[]{Element.WATER, Element.WIND, Element.FIRE})
                                .metal(Metal.BRONZE)
                                .vegetables(new Vegetable[]{Vegetable.ONION})
                                .build()
                },
                {
                        DataSample.builder()
                                .summary(new DataSample.Summary(SummaryOdd.FIVE, SummaryEven.SIX))
                                .elements(new Element[]{Element.WATER})
                                .color(Color.GREEN)
                                .metal(Metal.SELEN)
                                .vegetables(Vegetable.values())
                                .build()
                },
                {
                        DataSample.builder()
                                .elements(new Element[]{Element.FIRE})
                                .color(Color.BLUE)
                                .vegetables(new Vegetable[] {Vegetable.CUCUMBER, Vegetable.TOMATO, Vegetable.VEGETABLES})
                                .build()
                },
        };
    }
}
