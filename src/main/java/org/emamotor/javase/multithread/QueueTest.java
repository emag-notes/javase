package org.emamotor.javase.multithread;

import java.util.LinkedList;

/**
 * @author Yoshimasa Tanabe
 */
public class QueueTest {

    public static void main(String[] args) {

        Queue queue = new Queue();
        new Producer(queue).start();
        new Consumer(queue).start();

    }

}

class Queue {

    private LinkedList<Integer> queue;
    private static final int SIZE = 3;

    public Queue() {
        this.queue = new LinkedList<>();
    }

    public synchronized void put(Integer i) {

        while (this.queue.size() >= SIZE) {

            System.out.println("queue is full. wait to add " + i);

            try {
                wait();
            } catch (InterruptedException e) {
            }

        }

        queue.addFirst(i);
        System.out.println(queue);

        System.out.println("add " + i + " in queue");

        notifyAll();

    }

    public synchronized Object get() {

        while (this.queue.size() == 0) {

            System.out.println("queue is empty. waiting for adding data");

            try {
                wait();
            } catch (InterruptedException e) {
            }

        }

        Object obj = this.queue.removeLast();
        System.out.println(this.queue);

        System.out.println("get " + obj + " from queue");

        notifyAll();
        return obj;

    }

}

class Producer extends Thread {

    private Queue queue;

    public Producer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {

            try {
                Thread.sleep((long)(Math.random() * 1_000));
            } catch (InterruptedException e) {
            }

            queue.put(new Integer(i));

        }
    }

}

class Consumer extends Thread {

    private Queue queue;

    public Consumer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {

            try {
                Thread.sleep((long)(Math.random() * 1_000));
            } catch (InterruptedException e) {
            }

            this.queue.get();

        }
    }
}
