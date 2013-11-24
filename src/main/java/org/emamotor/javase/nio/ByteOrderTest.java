package org.emamotor.javase.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Date;
import java.util.Random;

/**
 * @author Yoshimasa Tanabe
 */
public class ByteOrderTest {

    public static void main(String[] args) {

        Random random = new Random();
        random.setSeed(new Date().getTime());
        ByteBuffer byteBuffer = ByteBuffer.allocate(10_000 * 4);

        // Big endian
        long start = System.currentTimeMillis();
        ByteOrder order = ByteOrder.BIG_ENDIAN;
        ByteBuffer tmpBuffer = byteBuffer.order(order);
        int[] ints = tmpBuffer.asIntBuffer().array();
        IntBuffer intBuffer = IntBuffer.wrap(ints);
        System.out.println("Exec as Big endian: ");
        printChunk(random, ints, intBuffer);

        long end = System.currentTimeMillis();
        System.out.println("Elapsed time:" + (end - start) + " msec");

        // Little endian
        start = System.currentTimeMillis();
        order = ByteOrder.LITTLE_ENDIAN;
        tmpBuffer = byteBuffer.order(order);
        ints = tmpBuffer.asIntBuffer().array();
        intBuffer = IntBuffer.wrap(ints);
        System.out.println("Exec as Big endian: ");
        printChunk(random, ints, intBuffer);

        end = System.currentTimeMillis();
        System.out.println("Elapsed time:" + (end - start) + " msec");

    }

    private static void printChunk(Random random, int[] ints, IntBuffer intBuffer) {
        int sum = 0;
        int count = 0;
        final int MAX = 10_000;

        for (; count < ints.length; count++) {
            int r = (int) (10 * random.nextDouble());
            sum += r;
            if (sum > MAX) {
                break;
            }
            intBuffer.put(r);
        }

        System.out.println("Element num: " + count);
        System.out.print("Buffer Data:");
        for (int i = 0; i < intBuffer.array().length; i++) {
            int data = intBuffer.array()[i];
            System.out.print(data);
            if (i < intBuffer.array().length - 1) {
                System.out.print(", ");
            }
        }

    }
}
