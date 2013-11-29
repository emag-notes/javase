package org.emamotor.javase.net;

import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MulticastChat extends JFrame implements ActionListener {

    public static final int MCAST_PORT = 10013;
    public static final int PACKET_SIZE = 1024;
    public static final String MCAST_ADDRESS = "224.0.1.1";
    public static final String ENCODING = "UTF-8";

    private JButton sendButton;
    private JButton joinButton;
    private JButton leaveButton;
    private JTextField sendTextField;
    private JTextArea receiveTextArea;
    private InetAddress mcastAddress;
    private MulticastSocket socket = null;

    public MulticastChat() {
        super("MulticastChat");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container = getContentPane();

        receiveTextArea = new JTextArea(10, 30);
        receiveTextArea.setEditable(false);
        receiveTextArea.setLineWrap(false);
        container.add(new JScrollPane(receiveTextArea), BorderLayout.CENTER);

        Box inputBox = Box.createVerticalBox();
        container.add(inputBox, BorderLayout.SOUTH);

        sendTextField = new JTextField(30);
        inputBox.add(sendTextField);

        JPanel buttonPanel = new JPanel();
        inputBox.add(buttonPanel);

        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        buttonPanel.add(sendButton, BorderLayout.WEST);

        joinButton = new JButton("Join");
        joinButton.addActionListener(this);
        buttonPanel.add(joinButton, BorderLayout.CENTER);

        leaveButton = new JButton("Leave");
        leaveButton.addActionListener(this);
        buttonPanel.add(leaveButton, BorderLayout.EAST);

        sendButton.setEnabled(false);
        leaveButton.setEnabled(false);

        pack();
    }

    public void start() {
        try {
            socket = new MulticastSocket(MCAST_PORT);
            mcastAddress = InetAddress.getByName(MCAST_ADDRESS);

            System.out.println("MulticatChat is running(port=" + socket.getLocalPort() + ")");

            byte buf[] = new byte[PACKET_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
            while (true) {
                socket.receive(receivePacket);
                String receiveMessage = new String(buf, 0, receivePacket.getLength(), ENCODING);
                receiveTextArea.append(receivePacket.getAddress()
                        + ": " + receiveMessage + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public void actionPerformed(ActionEvent event) {
        DatagramPacket sendPacket;
        if (event.getSource().equals(sendButton)) {
            try {
                String sendMessage;
                sendMessage = sendTextField.getText();
                byte[] buf = sendMessage.getBytes(ENCODING);
                sendPacket = new DatagramPacket(buf, buf.length, mcastAddress,
                        MCAST_PORT);
                socket.send(sendPacket);
                sendTextField.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (event.getSource().equals(joinButton)) {
            try {
                socket.joinGroup(mcastAddress);
                String joinMessage = "You joined Multicast group.";
                byte[] buf = joinMessage.getBytes(ENCODING);
                sendPacket = new DatagramPacket(buf, buf.length, mcastAddress,
                        MCAST_PORT);
                socket.send(sendPacket);
                sendButton.setEnabled(true);
                leaveButton.setEnabled(true);
                joinButton.setEnabled(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (event.getSource().equals(leaveButton)) {
            try {
                String leaveMessage = "Leaving the group.";
                byte[] buf = leaveMessage.getBytes(ENCODING);
                sendPacket = new DatagramPacket(buf, buf.length, mcastAddress,
                        MCAST_PORT);
                socket.send(sendPacket);
                socket.leaveGroup(mcastAddress);
                sendButton.setEnabled(false);
                leaveButton.setEnabled(false);
                joinButton.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        MulticastChat frame = new MulticastChat();
        frame.setVisible(true);
        frame.start();
    }

}
