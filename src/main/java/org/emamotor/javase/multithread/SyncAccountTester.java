package org.emamotor.javase.multithread;

/**
 * @author Yoshimasa Tanabe
 */
public class SyncAccountTester implements Runnable {

    private SyncAccount _account;

    public SyncAccountTester(SyncAccount account) {
        _account = account;
    }

    public static void main(String[] args) throws Exception {

        SyncAccount account = new SyncAccount();

        new Thread(new SyncAccountTester(account), "thread1").start();
        new Thread(new SyncAccountTester(account), "thread2").start();

    }

    @Override
    public void run() {

        try {

            for (int i = 0; i < 10; i++) {

                Thread.sleep((long) (Math.random() * 1000));

                _account.deposit(1000);
                _account.showBalance();

            }


        } catch (InterruptedException e) {
        }

    }

}
