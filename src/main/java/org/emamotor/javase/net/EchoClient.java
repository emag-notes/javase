package org.emamotor.javase.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Yoshimasa Tanabe
 */
public class EchoClient {

    public static final int ECHO_PORT = 10007;

    public static void main(String[] args) {

        Socket socket = null;

        try {

            socket = new Socket("localhost", ECHO_PORT);
            System.out.println("Connect: " + socket.getRemoteSocketAddress());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = keyIn.readLine()).length() > 0) {
                writer.println(input);
                String line = reader.readLine();
                if (line != null) {
                    System.out.println(line);
                } else {
                    break;
                }
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
