package org.emamotor.javase.multithread;

/**
 * @author Yoshimasa Tanabe
 */
public class Account {

    private int balance = 0;

    public void deposit (int money) {
        int total = balance + money;
        try {
            Thread.sleep((long)(Math.random() * 1000));
        } catch (InterruptedException e) {}
        balance = total;
    }

    public void showBalance() {
        System.out.println("[" + Thread.currentThread().getName() + "] Current Balance: " + balance);
    }

}
