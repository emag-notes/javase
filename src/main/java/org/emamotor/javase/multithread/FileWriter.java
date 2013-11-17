package org.emamotor.javase.multithread;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author Yoshimasa Tanabe
 */
public class FileWriter implements Runnable {

    private String text = null;

    @Override
    public void run() {

        try (BufferedWriter writer =
                     new BufferedWriter(new java.io.FileWriter("copy.txt"))) {

            writer.write(text);

        } catch (IOException e) {
            System.err.println("write error. cause: " + e.getClass() + ", " + e.getMessage());
        }

    }

    public void setText(String text) {
        this.text = text;
    }

}
