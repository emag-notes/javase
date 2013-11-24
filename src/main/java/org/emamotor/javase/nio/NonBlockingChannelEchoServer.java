package org.emamotor.javase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author Yoshimasa Tanabe
 */
public class NonBlockingChannelEchoServer {

    private static final int ECHO_PORT = 10007;
    private static final int BUF_SIZE = 1_000;

    private Selector selector;

    public static void main(String[] args) {
        new NonBlockingChannelEchoServer().run();
    }

    public void run() {
        ServerSocketChannel serverSocketChannel = null;
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(ECHO_PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            int localPort = serverSocketChannel.socket().getLocalPort();
            System.out.println("NonBlockingChannelEchoServer is running(port=" + localPort + ")");

            while (selector.select() > 0) {
                for (Iterator<SelectionKey> it = selector.selectedKeys().iterator(); it.hasNext();) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        doAccept((ServerSocketChannel) key.channel());
                    } else if (key.isReadable()) {
                        doRead((SocketChannel) key.channel());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocketChannel != null && serverSocketChannel.isOpen()) {
                System.out.println("NonBlockingChannelEchoServer is stopping.");
                try { serverSocketChannel.close(); } catch (IOException e) {}
            }
        }

    }

    private void doAccept(ServerSocketChannel serverSocketChannel) {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            String remoteAddress = socketChannel.socket().getRemoteSocketAddress().toString();
            System.out.println(remoteAddress + ":[Connect]");
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doRead(SocketChannel socketChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(BUF_SIZE);
        Charset charset = Charset.forName("UTF-8");
        String remoteAddress = socketChannel.socket().getRemoteSocketAddress().toString();
        try {
            if (socketChannel.read(buffer) < 0) {
                return;
            }
            buffer.flip();
            System.out.println(remoteAddress + ":" + charset.decode(buffer));
            buffer.flip();
            socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(remoteAddress + ":[Closed]");
            try { socketChannel.close(); } catch (IOException e) {}
        }

    }
}
