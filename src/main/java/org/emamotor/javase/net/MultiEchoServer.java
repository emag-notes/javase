package org.emamotor.javase.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Yoshimasa Tanabe
 */
public class MultiEchoServer {

    public static final int ECHO_PORT = 10007;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {

            serverSocket = new ServerSocket(ECHO_PORT);
            System.out.println("MultiEchoServer is running(port=" + serverSocket.getLocalPort());

            while (true) {
                Socket socket = serverSocket.accept();
                new EchoThread(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try { serverSocket.close(); } catch (IOException e) {}
            }
        }
    }

}

class EchoThread extends Thread {

    private Socket socket;

    public EchoThread(Socket socket) {
        this.socket = socket;
        System.out.println("Connected: " + socket.getRemoteSocketAddress());
    }

    @Override
    public void run() {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Received: " + line);
                writer.println(line);
                System.out.println("Send: " + line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try { socket.close(); } catch (IOException e) {}
            }
            System.out.println("Closed: " + socket.getRemoteSocketAddress());
        }

    }

}
