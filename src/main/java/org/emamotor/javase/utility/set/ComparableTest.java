package org.emamotor.javase.utility.set;

import org.emamotor.javase.utility.common.Human;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author emag
 */
public class ComparableTest {

    public static void main(String... args) {

        Set<Human> set = new TreeSet<>();

        set.add(new Human("Dave",  20));
        set.add(new Human("Alice", 16));
        set.add(new Human("Bob",   17));
        set.add(new Human("Carl",  18));

        set.add(new Human("Bob",   19));

        for (Iterator<Human> ite = set.iterator(); ite.hasNext();) {
            System.out.println(ite.next());
        }

    }

}