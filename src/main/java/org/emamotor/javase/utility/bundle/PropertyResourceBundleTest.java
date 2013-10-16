package org.emamotor.javase.utility.bundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author emag
 */
public class PropertyResourceBundleTest {

    public static void main(String... args) {

        Locale locale = null;

        if (args.length >= 1) {
            locale = new Locale(args[0]);
        } else {
            locale = Locale.ENGLISH;
        }

        ResourceBundle bundle = ResourceBundle.getBundle("hello", Locale.ENGLISH);
        System.out.println(bundle.getString("hello"));

    }
}
