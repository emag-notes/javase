package org.emamotor.javase.multithread;

import java.util.LinkedList;

/**
 * @author Yoshimasa Tanabe
 */
public class ATMSimulation {

    public static void main(String[] args) {

        ATMQueue queue = new ATMQueue();

        ATM atmA = new ATM(queue, "ATM_A");
        ATM atmB = new ATM(queue, "ATM_B");

        CustomerManager customerManager = new CustomerManager(queue);

        atmA.start();
        atmB.start();
        customerManager.start();

        long limit = 20_000;
        long start = System.currentTimeMillis();
        long now = System.currentTimeMillis();

        while (now < start + limit) {
            now = System.currentTimeMillis();
        }

        atmA.closeATM();
        atmB.closeATM();
        customerManager.closeBank();
        queue.stop();

    }
}


class ATMQueue {

    private LinkedList<Customer> queue;
    private boolean acceptance = true;

    public ATMQueue() {
        this.queue = new LinkedList<>();
    }

    public synchronized void stop() {
        this.acceptance = false;
        notifyAll();
    }


    public synchronized void put(Customer customer) {

        this.queue.addFirst(customer);
        System.out.println(this.queue);
        System.out.println("No." + customer.getNumber() + " is waiting.");
        notifyAll();

    }

    public synchronized Customer get() {

        while (this.queue.size() == 0 && acceptance) {

            System.out.println("queue is empty. waiting for adding data");

            try {
                wait();
            } catch (InterruptedException e) {
            }

        }

        Customer customer = null;
        if (this.queue.size() != 0) {
            customer = this.queue.removeLast();
        }
        System.out.println(this.queue);
        notifyAll();
        return customer;
    }

}

class CustomerManager extends Thread {

    private ATMQueue queue;
    private boolean open = true;

    public CustomerManager(ATMQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        int number = 1;

        while (this.open) {

            Customer customer = new Customer();
            customer.setNumber(number++);
            this.queue.put(customer);
            try {
                Thread.sleep((long) (Math.random() * 6000));
            } catch (InterruptedException e) {
            }
        }
    }

    public void closeBank() {
        this.open = false;
    }

}

class ATM extends Thread {

    private String atmName;
    private ATMQueue queue;
    private boolean open = true;

    public ATM(ATMQueue queue, String atmName) {
        this.queue = queue;
        this.atmName = atmName;
    }

    @Override
    public void run() {

        while (this.open) {

            Customer customer = this.queue.get();

            if (customer != null) {
                System.out.println("No." + customer.getNumber() + " is using " + this.atmName);

                try {
                    Thread.sleep((long) (Math.random() * 4000));
                } catch (InterruptedException e) {
                }
                System.out.println("No." + customer.getNumber() + " is done. " + this.atmName + " is empty.");
            }
        }

        System.out.println("ATM[" + this.atmName + "] is closed.");
    }

    public void closeATM() {
        this.open = false;
    }
}

class Customer {

    private int number;

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "number=" + number +
                '}';
    }
}