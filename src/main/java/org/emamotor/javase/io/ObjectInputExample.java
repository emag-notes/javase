package org.emamotor.javase.io;

import java.io.*;

/**
 * @author Yoshimasa Tanabe
 */
public class ObjectInputExample {

    public static void main(String[] args) {

        System.out.print("File name: ");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            String fileName = reader.readLine();

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Student student1 = (Student) in.readObject();
            Student student2 = (Student) in.readObject();

            System.out.println(student1);
            System.out.println(student2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
