package org.emamotor.javase.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * @author Yoshimasa Tanabe
 */
public class MulticastSender {

    public static final int ECHO_PORT = 10007;
    public static final String MULTICAST_ADDRESS = "224.0.1.1";

    public static void main(String[] args) {

        try (BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
             MulticastSocket socket = new MulticastSocket();) {

            InetAddress multicastAddress = InetAddress.getByName(MULTICAST_ADDRESS);

            String message;
            while ((message = keyIn.readLine()).length() > 0) {
                byte[] bytes = message.getBytes();
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, multicastAddress, ECHO_PORT);
                socket.send(packet);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
