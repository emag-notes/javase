package org.emamotor.javase.utility.map;

import org.emamotor.javase.utility.common.Human;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author emag
 */
public class TreeMapTest {

    public static void main(String... args) {

        Map<Human, String> humanAddressMap = new TreeMap<>();

        humanAddressMap.put(new Human("Alice", 16), "A city");
        humanAddressMap.put(new Human("Bob",   17), "B city");
        humanAddressMap.put(new Human("Carl",  19), "C city");

        for (Map.Entry<Human, String> humanAddress : humanAddressMap.entrySet()) {
            System.out.println(humanAddress.getKey() + "=" + humanAddress.getValue());
        }

    }

}
