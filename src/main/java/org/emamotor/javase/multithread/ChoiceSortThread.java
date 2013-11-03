package org.emamotor.javase.multithread;

/**
 * @author Yoshimasa Tanabe
 */
public class ChoiceSortThread extends Thread {

    private int[] array = new int[ThreadSpeedExample.LENGTH];

    @Override
    public void run() {
        System.out.println("[Choice sort begin]");

        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
             }
            if (min != i) {
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }
        System.out.println("[Choice sort end]");
    }

    public void setArray(int[] array) {
        this.array = array;
    }
}
