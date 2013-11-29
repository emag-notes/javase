package org.emamotor.javase.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author Yoshimasa Tanabe
 */
public class MulticastReceiver {

    public static final int ECHO_PORT = 10007;
    public static final int PACKET_SIZE = 1024;
    public static final String MULTICAST_ADDRESS = "224.0.1.1";

    public static void main(String[] args) {

        byte[] buffer = new byte[PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        try (MulticastSocket socket = new MulticastSocket(ECHO_PORT);) {

            InetAddress multicastAddress = InetAddress.getByName(MULTICAST_ADDRESS);
            socket.joinGroup(multicastAddress);
            System.out.println("MulticastReceiver is running(" +
                               "address=" + multicastAddress + ", " +
                               "port=" + socket.getLocalPort() + ")");

            while (true) {
                socket.receive(packet);
                String message = new String(buffer, 0, packet.getLength());
                System.out.println(packet.getSocketAddress() + " : " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
