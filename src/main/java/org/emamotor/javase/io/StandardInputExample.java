package org.emamotor.javase.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yoshimasa Tanabe
 */
public class StandardInputExample {

    enum FILE_OR_SOUT {FILE, SOUT}

    private static final String FILE_NAME = "standard-input-example.txt";

    public static void main(String[] args) {
        System.out.println("Input number to order.");
        System.out.println("Enter to finish.");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<Integer> list = new ArrayList<>();
        while (true) {
            try {
                line = reader.readLine();
                if ("".equals(line)) {
                    break;
                }
                list.add(Integer.valueOf(line));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(list);

        PrintStream out =  null;

        FILE_OR_SOUT fileOrSout = FILE_OR_SOUT.FILE;

        switch (fileOrSout) {
            case FILE:
                try {
                    out = new PrintStream(new FileOutputStream(FILE_NAME));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case SOUT:
                out = System.out;
                break;
            default:
                throw new RuntimeException("invalid type");
        }

        out.println("After sorted:");
        out.println(list);

        System.out.println("Complete!");
    }
}
