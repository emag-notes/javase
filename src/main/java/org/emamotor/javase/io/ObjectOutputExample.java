package org.emamotor.javase.io;

import java.io.*;

/**
 * @author Yoshimasa Tanabe
 */
public class ObjectOutputExample {

    public static void main(String[] args) {

        Student student1 = new Student();
        student1.setCource('A');
        student1.setId("X0001");
        student1.setLevel(5);
        student1.setName("Tanaka");

        Student student2 = new Student();
        student2.setCource('S');
        student2.setId("X0002");
        student2.setLevel(3);
        student2.setName("Yamamoto");

        String fileName = null;
        System.out.print("File name: ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            fileName = reader.readLine();

            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(student1);
            out.writeObject(student2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Save to " + fileName);

    }
}
