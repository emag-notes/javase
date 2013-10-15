package org.emamotor.javase.utility.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author emag
 */
public class HashSetTest {

    public static void main(String... args) {

        Set<Human> set = new HashSet<>();

        set.add(new Human("Alice", 16));
        set.add(new Human("Bob",   17));
        set.add(new Human("Carl",  18));

        set.add(new Human("Bob",   17));

        for (Iterator<Human> ite = set.iterator(); ite.hasNext();) {
            System.out.println(ite.next());
        }

    }

}