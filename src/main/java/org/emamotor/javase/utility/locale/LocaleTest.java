package org.emamotor.javase.utility.locale;

import java.util.Arrays;
import java.util.Locale;

/**
 * @author emag
 */
public class LocaleTest {

    public static void main(String... args) {

        Locale defaultLocale = Locale.getDefault();
        System.out.println(defaultLocale.getDisplayLanguage());
        System.out.println(defaultLocale.getDisplayCountry());

        Locale[] availableLocales = Locale.getAvailableLocales();
        System.out.println(Arrays.toString(availableLocales));

        for (Locale locale : availableLocales) {
            System.out.println(locale.getDisplayLanguage() + "=" + locale.getDisplayCountry());
        }
    }
}
