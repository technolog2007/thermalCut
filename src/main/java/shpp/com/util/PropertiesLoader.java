package shpp.com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shpp.com.app.MyApp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);
    private static final String PROPERTIES_FILE = "app.properties";
    private static final String PATH = "src/main/resources/";

    /**
     * The method loads data from the properties file into a buffer. The method works when running the program
     * from IDEA and external properties from Jar.
     * @return - link to properties buffer
     */
    public Properties loadProperties(){

        Properties properties = new Properties();

        try {
            InputStream rootPath = MyApp.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
            properties.load(rootPath);
            return properties;
        } catch (Exception e) {
            try (FileInputStream inputStream = new FileInputStream(PATH + PROPERTIES_FILE)) {
                properties.load(inputStream);
            } catch (IOException exception) {
                logger.error("Something went wrong with reading the properties!");
            }
            return properties;
        }
    }

}
