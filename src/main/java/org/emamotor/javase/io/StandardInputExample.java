package org.emamotor.javase.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yoshimasa Tanabe
 */
public class StandardInputExample {

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

        System.out.println("After sorted:");
        System.out.println(list);
    }
}
