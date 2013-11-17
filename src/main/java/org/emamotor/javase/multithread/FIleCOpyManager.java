package org.emamotor.javase.multithread;

/**
 * @author Yoshimasa Tanabe
 */
public class FIleCOpyManager implements Runnable {

    public static void main(String[] args) {
        new Thread(new FIleCOpyManager()).start();
    }

    @Override
    public void run() {
        System.out.println("[File copy begin]");

        FileReader reader = new FileReader();
        Thread readerThread = new Thread(reader);

        readerThread.start();
        try {

            System.out.println("[File read begin]");
            readerThread.join();
            System.out.println("[File read end]");

        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        FileWriter writer = new FileWriter();
        writer.setText(reader.getText());
        Thread writerThread = new Thread(writer);

        writerThread.start();
        try {

            System.out.println("[File write begin]");
            writerThread.join();
            System.out.println("[File write end]");

        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        System.out.println("[File copy end]");

    }
}
