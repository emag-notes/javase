package org.emamotor.javase.multithread;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Yoshimasa Tanabe
 */
public class FileReader implements Runnable {

    private static final String PATH = "src/main/resources/data.txt";
    private String text = null;

    @Override
    public void run() {

        try (BufferedReader reader =
                     new BufferedReader(new java.io.FileReader(PATH))){

            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            text = sb.toString();

        } catch (IOException e) {
            System.err.println("read error. cause: " + e.getClass() + ", " + e.getMessage());
        }

    }

    public String getText() {
        return this.text;
    }

}
