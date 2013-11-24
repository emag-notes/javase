package org.emamotor.javase.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Yoshimasa Tanabe
 */
public class PrintWriterExample {

    private static final int NATURAL_NUM = 10;

    public static void main(String[] args) {

        try (FileOutputStream out = new FileOutputStream("natural-number.txt");
             PrintWriter writer = new PrintWriter(out);) {
            for (int i = 1; i <= NATURAL_NUM; i++) {
                writer.println(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
