package org.emamotor.javase.utility.map;

import org.emamotor.javase.utility.common.Human;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author emag
 */
public class LinkedHashMapTest {

    public static void main(String... args) {

        Map<Human, String> humanAddressMap = new LinkedHashMap<>();

        humanAddressMap.put(new Human("Carl",  19), "C city");
        humanAddressMap.put(new Human("Alice", 16), "A city");
        humanAddressMap.put(new Human("Bob",   17), "B city");

        for (Map.Entry<Human, String> humanAddress : humanAddressMap.entrySet()) {
            System.out.println(humanAddress.getKey() + "=" + humanAddress.getValue());
        }

    }

}
