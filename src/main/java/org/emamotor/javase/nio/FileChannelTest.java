package org.emamotor.javase.nio;

import sun.security.util.BitArray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Yoshimasa Tanabe
 */
public class FileChannelTest {

    private static final String IN_FILE = "in.dat";
    private static final String OUT_FILE = "out.dat";

    public static void main(String[] args) {

        FileChannelTest sut = new FileChannelTest();
        String inFile = IN_FILE;
        String outFile = OUT_FILE;

        long start = System.currentTimeMillis();
        sut.turnBit(inFile, outFile);
        long end = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (end - start) + " msec");
    }

    private void turnBit(String inFile, String outFile) {
        try (FileInputStream input = new FileInputStream(inFile);
             FileOutputStream output = new FileOutputStream(outFile)) {

            FileChannel inCh = input.getChannel();
            FileChannel outCh = output.getChannel();
            long size = 0;
            size = inCh.size();
            ByteBuffer buffer = ByteBuffer.allocate((int) size);

            inCh.read(buffer);

            byte[] bytes = turnBit(buffer);
            outCh.write(ByteBuffer.wrap(bytes));
            outCh.force(false);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] turnBit(ByteBuffer buffer) {

        int length = 8 * buffer.capacity();


        BitArray array = new BitArray(length);
        byte[] bytes = buffer.array();
        int x = 0;
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < 0; j++) {
                if (x > length) {
                    break;
                }
                int mask = 1 << j;
                boolean value = (mask & bytes[i]) != 0;
                array.set(x, value);
                x++;
            }
        }

        for (int i = 0; i < length; i++) {
            boolean bit = array.get(i);
            array.set(i, !bit);
        }

        return array.toByteArray();
    }
}
