package org.emamotor.javase.utility.arraycollection;

import org.emamotor.javase.utility.common.Human;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author emag
 */
public class CollectionsAPITest {

    private static final int NUM = 10;

    public static void main(String... args) {

        List<String> strings = new ArrayList<>();

        for (int i = 0; i < NUM; i++) {
            strings.add("String_" + i);
        }

        // Initial
        System.out.println("Initial: " + strings);

        // Reverse
        Collections.reverse(strings);
        System.out.println("Reverse: " + strings);

        // Rotate
        Collections.rotate(strings, 3);
        System.out.println("Rotate:  " + strings);

        // Shuffle
        Collections.shuffle(strings);
        System.out.println("Shuffle: " + strings);

        // Swap
        Collections.swap(strings, 2, 4);
        System.out.println("Swap:    " + strings);


        System.out.println();
        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < NUM; i++) {
            integers.add(i);
        }

        System.out.println("Initial:      " + integers);

        // ReverseOrder
        Collections.sort(integers, Collections.reverseOrder());
        System.out.println("ReverseOrder: " + integers);

        List<Integer> unmodifiableIntegerList = Collections.unmodifiableList(integers);

        try {
            unmodifiableIntegerList.add(99);
        } catch (Exception e) {
            System.out.println("Catch Exception is " + e.getClass());
        } finally {
            System.out.println("UnmodIntegerList:    " + unmodifiableIntegerList);
        }

        try {
            unmodifiableIntegerList.set(0, 99);
        } catch (Exception e) {
            System.out.println("Catch Exception is " + e.getClass());
        } finally {
            System.out.println("UnmodIntegerList:    " + unmodifiableIntegerList);
        }

        System.out.println();
        List<Human> humans = new ArrayList<>();

        humans.add(new Human("Alice", 16));
        humans.add(new Human("Bob",   17));
        humans.add(new Human("Carl",  18));

        System.out.println("Initial:           " + humans);

        List<Human> unmodifiableHumanList = Collections.unmodifiableList(humans);

        try {
            Human human = unmodifiableHumanList.get(0);
            human.setAge(99);
        } catch (Exception e) {
            System.out.println("Catch Exception is " + e.getClass());
        } finally {
            System.out.println("UnmodHumanList:    " + unmodifiableHumanList);
        }

    }
}
