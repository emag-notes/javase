package org.emamotor.javase.multithread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Yoshimasa Tanabe
 */
public class MyTaskInFixedThreadPool implements Runnable {

    private String tab = "";

    public MyTaskInFixedThreadPool(int tab) {
        for (int i = 0; i < tab; i++) {
            this.tab += " ";
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(this.tab + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        System.out.println("[Sending Tasks...]");
        for (int i = 0; i < 3; i++) {
            service.execute(new MyTaskInFixedThreadPool(i * 3));
        }
        System.out.println("[Finish Sending!]");

        service.shutdown();
        System.out.println("[Shutdown]");
    }
}
