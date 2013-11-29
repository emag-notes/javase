package org.emamotor.javase.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Yoshimasa Tanabe
 */
public class DatagramReceiver {

    public static final int SERVER_PORT = 10007;
    public static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {

        byte[] buffer = new byte[PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        try (DatagramSocket socket = new DatagramSocket(SERVER_PORT);) {

            System.out.println("DatagramReceiver is running(port=" + socket.getLocalPort() + ")");

            while (true) {
                socket.receive(packet);
                String message = new String(buffer, 0, packet.getLength());
                System.out.println(packet.getSocketAddress() + " Received: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
