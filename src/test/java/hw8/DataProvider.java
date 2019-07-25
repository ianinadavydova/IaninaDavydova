package hw8;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hw7hw8.entities.MetalsColors;
import hw7hw8.entities.User;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataProvider {
    static class SerializedMetalsColors {
        public List<Integer> summary;
        public List<String> elements;
        public String color;
        public String metals;
        public List<String> vegetables;
    }

    @org.testng.annotations.DataProvider(name = "dataSet")
    Object[][] metalsColorsDataSet() throws IOException {
        Gson gson = new Gson();
        Type serializedDataSetType = new TypeToken<Map<String, SerializedMetalsColors>>() {}.getType();
        Map<String, SerializedMetalsColors> dataSet;
        try (Reader reader = new FileReader("src/test/resources/hw8/JDI_ex8_metalsColorsDataSet.json")){
            dataSet = gson.fromJson(reader, serializedDataSetType);
        }
        List<Object[]> result = new ArrayList<>(dataSet.size());
        User user = User.readPredefinedUser();

        for (SerializedMetalsColors dataSample : dataSet.values()) {
            MetalsColors formData = new MetalsColors().set(entity -> {
                entity.elements = dataSample.elements;
                entity.colors = dataSample.color;
                entity.metals = dataSample.metals;
                entity.vegetables = dataSample.vegetables;
            });
            int sum = 0;
            for (Object o : dataSample.summary) {
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
                    "Elements: " + String.join(", ", dataSample.elements),
                    "Color: " + dataSample.color,
                    "Metal: " + dataSample.metals,
                    "Vegetables: " + String.join(", ", dataSample.vegetables)
            );
            result.add(new Object[] { user, formData, expectedLog });
        }
        return result.toArray(new Object[0][]);
    }
}
