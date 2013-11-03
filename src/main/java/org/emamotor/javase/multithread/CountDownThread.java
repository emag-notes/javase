package org.emamotor.javase.multithread;

/**
 * @author Yoshimasa Tanabe
 */
public class CountDownThread extends Thread {

    private String name;

    public CountDownThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 3; i >= 0; i--) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.println(this.name + " : " + i);
        }
    }

    public static void main(String[] args) {
        CountDownThread t1 = new CountDownThread("thread-1");
        CountDownThread t2 = new CountDownThread("thread-2");
        t1.start();
        t2.start();
    }
}
