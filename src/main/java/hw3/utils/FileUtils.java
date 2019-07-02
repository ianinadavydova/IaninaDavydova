package hw3.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtils {

    public static Properties readPropertiesFromFile(String filePath) throws IOException {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(filePath)) {

            prop.load(input);

        }
        return prop;
    }
}
