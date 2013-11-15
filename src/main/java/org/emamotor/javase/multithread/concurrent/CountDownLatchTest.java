package org.emamotor.javase.multithread.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    public static void main(String[] args) throws Exception {

        CountDownLatch startGate = new CountDownLatch(2);
        CountDownLatch endGate = new CountDownLatch(5);

        Random random = new Random();

        // start 5 threads
        for (int i = 0; i < 5; i++) {
            new TestThread(
                    startGate, endGate, i + 1, random.nextInt(10) + 1)
                    .start();
        }

        System.out.println("sleep 5 sec.");
        TimeUnit.SECONDS.sleep(5);

        System.out.println("Open startGate 1/2");
        startGate.countDown();
        System.out.println("sleep 5 sec.");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Open startGate 2/2");
        startGate.countDown();

        endGate.await();
        System.out.println("End all threads");

    }

    static class TestThread extends Thread {

        final CountDownLatch startGate;
        final CountDownLatch endGate;
        final int no;
        final int sec;

        public TestThread(CountDownLatch startGate,
                          CountDownLatch endGate,
                          int no,
                          int sec) {

            this.startGate = startGate;
            this.endGate = endGate;
            this.no = no;
            this.sec = sec;

        }

        @Override
        public void run() {

            try {

                System.out.println("[Before start] No." + this.no + "(" + this.sec + " sec.)");
                this.startGate.await();

                System.out.println("[start] No." + this.no);
                TimeUnit.SECONDS.sleep(sec);

                System.out.println("[end] No." + this.no);
                this.endGate.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
