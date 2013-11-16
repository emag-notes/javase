package org.emamotor.javase.multithread;

import java.util.Random;

/**
 * @author Yoshimasa Tanabe
 */
public class PriorityExample {

    public static final int LENGTH = 20_000;

    public static void main(String[] args) {

        ChoiceSortThread choice = new ChoiceSortThread();
        BubbleSortThread bubble = new BubbleSortThread();

        int[] array1 = new int[LENGTH];
        int[] array2 = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            array1[i] = new Random().nextInt(1000);
            array2[i] = array1[i];
        }

        choice.setArray(array1);
        bubble.setArray(array2);

        choice.setPriority(7);
        bubble.setPriority(10);

        bubble.start();
        choice.start();

    }
}
