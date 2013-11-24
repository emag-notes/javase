package org.emamotor.javase.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author Yoshimasa Tanabe
 */
public class ChannelEchoCLient {

    public static final int ECHO_POINT = 10007;
    public static final int BUF_SIZE = 1_000;

    public static void main(String[] args) {

        SocketChannel channel = null;
        ByteBuffer buffer = ByteBuffer.allocate(BUF_SIZE);
        Charset charset = Charset.forName("UTF-8");

        try {
            channel = SocketChannel.open(new InetSocketAddress("localhost", ECHO_POINT));
            BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Send: ");
            String line = keyin.readLine();
            channel.write(charset.encode(CharBuffer.wrap(line + "\n")));
            while (channel.isConnected()) {
                buffer.clear();
                if (channel.read(buffer) < 0) {
                    return;
                }
                buffer.flip();
                System.out.print("Receive: " + charset.decode(buffer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null && channel.isOpen()) {
                try { channel.close(); } catch (IOException e) {}
            }
        }
    }
}
