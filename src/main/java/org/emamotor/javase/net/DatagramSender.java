package org.emamotor.javase.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author Yoshimasa Tanabe
 */
public class DatagramSender {

    public static final int SERVER_PORT = 10007;

    public static void main(String[] args) {


        InetSocketAddress remoteAddress = new InetSocketAddress("localhost", SERVER_PORT);

        try (BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
             DatagramSocket datagramSocket = new DatagramSocket()) {

            String message;
            while ((message = keyIn.readLine()).length() > 0) {
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, remoteAddress);
                datagramSocket.send(packet);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

}
