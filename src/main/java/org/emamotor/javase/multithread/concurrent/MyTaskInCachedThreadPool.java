package org.emamotor.javase.multithread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Yoshimasa Tanabe
 */
public class MyTaskInCachedThreadPool implements Runnable {

    private int number;

    public MyTaskInCachedThreadPool(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Task " + this.number + " Start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task " + this.number + " End");
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Thread.currentThread().getThreadGroup().list();
        System.out.println("Sending Tasks...");
        for (int i = 0; i < 3; i++) {
            service.execute(new MyTaskInCachedThreadPool(i));
            Thread.currentThread().getThreadGroup().list();
            try {
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }
}
