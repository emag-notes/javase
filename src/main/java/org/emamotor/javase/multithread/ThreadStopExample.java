package org.emamotor.javase.multithread;

import java.util.Random;

/**
 * @author Yoshimasa Tanabe
 */
public class ThreadStopExample {

    public static final int LENGTH = 20_000;
    private static boolean running = true;
    private static final int timeout = 10;

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

        long start = System.currentTimeMillis();

        bubble.start();
        choice.start();

        long now;

        while (running) {
            now = System.currentTimeMillis();

            if (now > timeout + start) {
                choice.stopRunning();
                bubble.stopRunning();
                break;
            }

        }

    }

    public static void finish() {
        running = false;
    }
}
