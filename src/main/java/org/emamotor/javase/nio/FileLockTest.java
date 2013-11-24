package org.emamotor.javase.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author Yoshimasa Tanabe
 */
public class FileLockTest {

    private static final String COUNTER_FILE = "counter.dat";
    private static final long INIT_START = 1;

    public static void main(String[] args) {

        FileChannel inCh = null;

        File counter = new File(COUNTER_FILE);
        FileChannel outCh = null;

        if (!counter.exists()) {
            try {
                counter.createNewFile();
                RandomAccessFile out = new RandomAccessFile(counter, "rw");
                outCh = out.getChannel();

                MappedByteBuffer buffer = outCh.map(FileChannel.MapMode.READ_WRITE, 0, 8);

                buffer.putLong(INIT_START);
                outCh.write(buffer);
                outCh.force(false);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try { outCh.close(); } catch (IOException e) {}
            }

            return;
        }

        try (RandomAccessFile out = new RandomAccessFile(counter, "rw");) {
            inCh = out.getChannel();
            counter(inCh);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void counter(FileChannel inCh) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.position(0);

        FileLock lock = inCh.tryLock();
        inCh.read(byteBuffer);
        byteBuffer.position(0);
        long counter = byteBuffer.getLong();
        byteBuffer.position(0);
        byteBuffer.putLong(++counter);
        byteBuffer.position(0);

        lock.release();

        FileChannel channel = new FileInputStream(new File(COUNTER_FILE)).getChannel();
        channel.write(byteBuffer);
        channel.force(true);
        channel.close();

        inCh.close();
    }
}
