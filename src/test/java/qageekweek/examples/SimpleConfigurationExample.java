package qageekweek.examples;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class SimpleConfigurationExample {

    public static void main(String[] args) {
        try (InputStream input = new FileInputStream("config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("browser"));
            System.out.println(prop.getProperty("defaultUser"));
            System.out.println(prop.getProperty("defaultPassword"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
