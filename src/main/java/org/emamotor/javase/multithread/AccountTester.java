package org.emamotor.javase.multithread;

import java.util.concurrent.CountDownLatch;

/**
 * @author Yoshimasa Tanabe
 */
public class AccountTester implements Runnable {

    private Account _account;

    public AccountTester(Account account) {
        _account = account;
    }

    public static void main(String[] args) throws Exception {

        Account account = new Account();

        new Thread(new AccountTester(account), "thread1").start();
        new Thread(new AccountTester(account), "thread2").start();

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
