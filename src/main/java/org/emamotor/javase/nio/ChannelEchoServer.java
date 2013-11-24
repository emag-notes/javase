package org.emamotor.javase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author Yoshimasa Tanabe
 */
public class ChannelEchoServer {

    public static final int ECHO_PORT = 10007;

    public static void main(String[] args) {

        new ChannelEchoServer().run();

    }

    public void run() {

        ServerSocketChannel serverSocketChannel = null;

        try  {

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(ECHO_PORT));
            System.out.println("ChannelEchoServer is running(port=" + serverSocketChannel.socket().getLocalPort());

            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println(socketChannel.socket().getRemoteSocketAddress() + ":[Connect]");
                new Thread(new ChannelEchoThread(socketChannel)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocketChannel != null && serverSocketChannel.isOpen()) {
                try {
                    System.out.println("ChannelEchoServer is stopping.");
                    serverSocketChannel.close();
                } catch (IOException e) {}
            }
        }
    }
}

class ChannelEchoThread implements Runnable {

    private static final int BUF_SIZE = 1_000;
    SocketChannel channel = null;

    public ChannelEchoThread(SocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(BUF_SIZE);
        Charset charset = Charset.forName("UTF-8");
        String remoteAddress = null;
        try {
            remoteAddress = this.channel.getRemoteAddress().toString();
            if (this.channel.read(buffer) < 0) {
                return;
            }
            buffer.flip();
            String input = charset.decode(buffer).toString();
            System.out.print(remoteAddress + ":" + input);
            buffer.flip();
            this.channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(remoteAddress + ":[Closed]");
            if (this.channel != null && this.channel.isOpen()) {
                try { this.channel.close(); } catch (IOException e) {}
            }
        }
    }
}
