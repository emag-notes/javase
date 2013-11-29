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
public class EchoServer {

    public static final int ECHO_PORT = 10007;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket socket = null;

        try {

            serverSocket = new ServerSocket(ECHO_PORT);
            System.out.println("EchoServer is running(port=" + serverSocket.getLocalPort());

            socket = serverSocket.accept();
            System.out.println("Connected: " + socket.getRemoteSocketAddress());

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
            if (serverSocket != null) {
                try { serverSocket.close(); } catch (IOException e) {}
            }
        }
    }

}
