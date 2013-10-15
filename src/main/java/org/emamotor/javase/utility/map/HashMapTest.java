package org.emamotor.javase.utility.map;

import org.emamotor.javase.utility.common.Human;

import java.util.HashMap;
import java.util.Map;

/**
 * @author emag
 */
public class HashMapTest {

    public static void main(String... args) {

        Map<Human, String> humanAddressMap = new HashMap<>();

        humanAddressMap.put(new Human("Alice", 16), "A city");
        humanAddressMap.put(new Human("Bob",   17), "B city");
        humanAddressMap.put(new Human("Carl",  19), "C city");

        for (Map.Entry<Human, String> humanAddress : humanAddressMap.entrySet()) {
            System.out.println(humanAddress.getKey() + "=" + humanAddress.getValue());
        }

    }

}
