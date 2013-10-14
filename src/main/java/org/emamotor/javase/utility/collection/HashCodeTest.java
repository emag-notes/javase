package org.emamotor.javase.utility.collection;

/**
 * @author emag
 */
public class HashCodeTest {

    private static final String[] testStrings = {
            "test",
            new String("test"),
            "te" + "st",
            "te" + new String("st"),
            new String("te") + new String("st"),
    };

    public static void main(String... args) {

        for (int i = 0; i < testStrings.length; i++) {
            for (int j = 0; j < testStrings.length; j++) {
                System.out.println(i + ":" + j);
                System.out.println("equals: " + testStrings[i].equals(testStrings[j]));
                System.out.println("==: " + (testStrings[i] == testStrings[j]));
            }
        }
    }

}
