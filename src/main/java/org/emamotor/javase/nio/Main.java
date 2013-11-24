package org.emamotor.javase.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author Yoshimasa Tanabe
 */
public class Main {

    public static void main(String[] args) {

        Main buffer = new Main();
//        int[] sizes = {100, 1_000, 10_000, 100_000, 1_000_000};

        try {
            for (int i = 1; i < Integer.MAX_VALUE; i++) {
                buffer.benchMark(i);
                System.out.println();
            }
        } catch (OutOfMemoryError oome) {
            System.out.println("OOME occurred");
            System.out.println(oome.getMessage());
        }

    }

    private void benchMark(int size) {

        System.out.println("Size\tType\t\tTime");
        System.out.println("------------------------");

        benchMark(size, true);
        benchMark(size, false);
    }

    private void benchMark(int size, boolean isDirect) {

        IntBuffer buffer = null;

        if (isDirect) {
            buffer = ByteBuffer.allocateDirect(size * 4).asIntBuffer();
        } else {
            buffer = IntBuffer.allocate(size);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            buffer.put(i, i);
        }

        int[] ints;
        boolean bOK = true;

        try {
            ints = buffer.array();
        } catch (UnsupportedOperationException e) {
            bOK = false;
        }

        long end = System.currentTimeMillis();

        String bufferKind = isDirect ? "Direct" : "Not Direct";
        System.out.print(size + "\t");
        System.out.print(bufferKind + "\t");
        System.out.print((end - start) + "msec\t");
        System.out.println(bOK ? "OK" : "IntBuffer#array() is not supported");
    }
}
