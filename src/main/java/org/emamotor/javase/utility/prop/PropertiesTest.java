package org.emamotor.javase.utility.prop;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;

/**
 * @author emag
 */
public class PropertiesTest {

    private static final String PATH = "/hello.properties";

    public static void main(String... args) {

        Properties props = new Properties();

        InputStream is = PropertiesTest.class.getClass().getResourceAsStream(PATH);

        try (Reader reader = new InputStreamReader(is, "UTF-8")) {

            props.load(reader);

            for (Map.Entry prop : props.entrySet()) {
                System.out.println(prop.getKey() + ":" + prop.getValue());
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("locale.properties may be invalid.");
        }
    }

}
