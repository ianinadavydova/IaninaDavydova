package hw3.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtils {

    public static Properties readPropertiesFromFile(String filePath) {
        Properties prop = new Properties();

        try (InputStream input = FileUtils.class.getClass().getClassLoader()
                .getResourceAsStream(filePath)) {
            if (input == null) {
                System.out.println("Sorry, unable to find file at " + filePath);
            }
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
