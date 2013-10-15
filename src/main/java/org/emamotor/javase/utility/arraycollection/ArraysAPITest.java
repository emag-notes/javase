package org.emamotor.javase.utility.arraycollection;

import java.util.Arrays;
import java.util.List;

/**
 * @author emag
 */
public class ArraysAPITest {

    public static void main(String... args) {

        float[] floats = {
                3F,
                1F,
                7F,
                2F,
                10F,
                4F,
                6F,
                8F,
                9F,
                5F,
        };

        // Initial
        System.out.println("Initial: " + Arrays.toString(floats));

        // Sorted
        Arrays.sort(floats);
        System.out.println("Sorted: " + Arrays.toString(floats));

        // BinarySearch
        System.out.println("3F at " + Arrays.binarySearch(floats, 3F));

        // Fill1
        Arrays.fill(floats, 1, 5, 99F);
        System.out.println("Fill1: " + Arrays.toString(floats));

        // Fill2
        Arrays.fill(floats, 0F);
        System.out.println("Fill2: " + Arrays.toString(floats));

        // asList
        String[] strings = {
                "Alice",
                "Bob",
                "Carl",
                "Dave",
                "Edo",
        };

        List<String> stringList = Arrays.asList(strings);

        System.out.println("Before: " + Arrays.toString(strings));

        stringList.set(2, "cURL");

        System.out.println("After: " + Arrays.toString(strings));

        try {
            stringList.add("Fran");
        } catch (Exception e) {
            System.out.println("Catch Exception is " + e.getClass());
            System.out.println("Original Array: " + Arrays.toString(strings));
        }

        try {
            stringList.remove(3);
        } catch (Exception e) {
            System.out.println("Catch Exception is " + e.getClass());
            System.out.println("Original Array: " + Arrays.toString(strings));
        }

    }
}
