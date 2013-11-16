package org.emamotor.javase.multithread;

/**
 * @author Yoshimasa Tanabe
 */
public class BubbleSortThread extends Thread implements Stoppable {

    private int[] array = new int[ThreadSpeedExample.LENGTH];
    private boolean stop = false;

    @Override
    public void run() {
        System.out.println("[Bubble sort begin]");

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }

            if (stop) {
                System.out.println("[Bubble sort timeout]");
                return;
            }

        }
        System.out.println("[Bubble sort end]");
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    @Override
    public void stopRunning() {
        this.stop = true;
    }
}
