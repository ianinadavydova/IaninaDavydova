package hw7hw8;

import hw3.enums.Color;
import hw3.enums.Element;
import hw3.enums.Metal;
import hw4.enums.Vegetable;
import hw7hw8.entities.MetalsColorsEntity;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "staticDataSet")
    Object[][] staticDataSet() {
        return new Object[][]{
                {
                        new MetalsColorsEntity().set(entity -> {
                            entity.summaryOdd = "3";
                            entity.summaryEven = "8";
                            entity.elements = Arrays.asList(Element.WATER, Element.FIRE);
                            entity.colors = Color.RED.getText();
                            entity.metals = Metal.SELEN.getName();
                            entity.vegetables = Arrays.asList(Vegetable.CUCUMBER, Vegetable.TOMATO);
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

    @org.testng.annotations.DataProvider(name = "dynamicDataSet")
    Object[][] dynamicDataSet() throws IOException {
        File file = new File("src/test/resources/hw8/JDI_ex8_metalsColorsDataSet.json");
        String content = FileUtils.readFileToString(file, "utf-8");
        Map<String, Object> serializedData = new JSONObject(content).toMap();
        List<Object[]> result = new ArrayList<>(serializedData.size());
        for (Object sample : serializedData.values()) {
            Map<String, Object> dict = (Map<String, Object>) sample;
            List<Object> summary = (List<Object>) dict.get("summary");
            List<Element> elements = deserialize(dict, "elements", DataProvider::deserializeElement);
            String color = (String) dict.get("color");
            String metal = (String) dict.get("metals");
            List<Vegetable> vegetables = deserialize(dict, "vegetables", DataProvider::deserializeVegetable);
            MetalsColorsEntity formData = new MetalsColorsEntity().set(entity -> {
                entity.elements = elements;
                entity.colors = color;
                entity.metals = metal;
                entity.vegetables = vegetables;
            });
            int sum = 0;
            for (Object o : summary) {
                int i = (Integer) o;
                if (i % 2 == 0) {
                    formData.summaryEven = Integer.toString(i);
                } else {
                    formData.summaryOdd = Integer.toString(i);
                }
                sum += i;
            }
            List<String> expectedLog = Arrays.asList(
                    "Summary: " + sum,
                    "Elements: " + elements.stream().map(Element::getName).collect(Collectors.joining(", ")),
                    "Color: " + color,
                    "Metal: " + metal,
                    "Vegetables: " + vegetables.stream().map(Vegetable::getName).collect(Collectors.joining(", "))
            );
            result.add(new Object[] { formData, expectedLog });
        }
        return result.toArray(new Object[0][]);
    }

    private static <E> List<E> deserialize(Map<String, Object> data, String key,
                                           Function<Object, E> deserializer) {
        return ((List<Object>) data.get(key)).stream()
                .map(deserializer)
                .collect(Collectors.toList());
    }

    private static Element deserializeElement(Object o) {
        String name = (String) o;
        return Arrays.stream(Element.values()).filter(e -> e.getName().equals(name)).findFirst().get();
    }

    private static Vegetable deserializeVegetable(Object o) {
        String name = (String) o;
        return Arrays.stream(Vegetable.values()).filter(e -> e.getName().equals(name)).findFirst().get();
    }
}