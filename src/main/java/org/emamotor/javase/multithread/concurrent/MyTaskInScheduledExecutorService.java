package org.emamotor.javase.multithread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Yoshimasa Tanabe
 */
public class MyTaskInScheduledExecutorService implements Runnable {

    int number;
    long start;

    public MyTaskInScheduledExecutorService(int number, long start) {
        this.number = number;
        this.start = start;
    }

    @Override
    public void run() {
        System.out.print("Task " + this.number + " Start");
        printElapsedTime(this.start);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Task " + this.number + " End");
        printElapsedTime(this.start);
    }

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 3; i ++) {
            System.out.print("Task " + i + " Send ");
            printElapsedTime(start);
            service.schedule(new MyTaskInScheduledExecutorService(i, start), 1_000, TimeUnit.MILLISECONDS);
//            service.scheduleAtFixedRate(new MyTaskInScheduledExecutorService(i, start), 1000, 10_000, TimeUnit.MILLISECONDS);
//            service.scheduleWithFixedDelay(new MyTaskInScheduledExecutorService(i, start), 1000, 10_000, TimeUnit.MILLISECONDS);
        }
    }

    private static void printElapsedTime(long start) {
        System.out.println("(" +(System.currentTimeMillis() - start) + ")");
    }

}
