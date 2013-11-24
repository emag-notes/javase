package org.emamotor.javase.nio;

import java.nio.IntBuffer;
import java.util.Date;
import java.util.Random;

/**
 * @author Yoshimasa Tanabe
 */
public class BufferArrayTest {

    public static void main(String[] args) {

        int[] ints = new int[10_000];
        IntBuffer buffer = IntBuffer.wrap(ints);

        Random random = new Random();
        random.setSeed((int) new Date().getTime());

        int sum = 0;
        int count = 0;
        final int MAX = 10_000;
        for (; count < ints.length; count++) {
            int r = random.nextInt(10) + 1;
            sum += r;
            if (sum > MAX) {
                break;
            }
            buffer.put(r);
        }

        System.out.println("Elements num: " + count);

        System.out.print("Buffer Data : ");
        for (int i = 0; i < buffer.array().length; i++) {
            int data = buffer.array()[i];
            System.out.print(i);
            if (i < buffer.array().length - 1) {
                System.out.print(", ");
            }
        }

    }
}
