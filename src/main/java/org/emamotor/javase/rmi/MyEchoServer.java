package org.emamotor.javase.rmi;

import java.net.InetAddress;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author tanabe
 */
public class MyEchoServer implements Echo {

    public static void main(String[] args) {

        try {

            MyEchoServer server = new MyEchoServer();
            System.out.println("------ Info -------");
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Host Address(A)  : " + hostAddress);
            System.out.println("ServerHostName(B): " + System.getProperties().getProperty("java.rmi.server.hostname"));
            System.out.println("Host Name(C)     : " + InetAddress.getLocalHost().getHostName());
            System.out.println("Host FQDN(C)     : " + InetAddress.getByName(hostAddress).getHostName());
            System.out.println("LocalHostName?   : " + System.getProperties().getProperty("java.rmi.server.useLocalHostName"));
            System.out.println("-------------------");

            // start RMI server
            server.createAndBindRegistry("Echo", server, 1099);
            System.out.println("Ready!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String echo(String s) throws RemoteException {
        StringBuilder echoMessage = new StringBuilder("ECHO: ").append(s);
        System.out.println(echoMessage);
        return echoMessage.toString();
    }

    private void createAndBindRegistry(String refName, Remote remote, int port)
                                    throws RemoteException, AlreadyBoundException {
        System.out.println("Exporting stub...");
        // export remote method stub
        Remote stub = UnicastRemoteObject.exportObject(remote, 0);

        System.out.println("Creating registry...");
        // get RMI registry on port 1099
        Registry registry = LocateRegistry.createRegistry(port);

        System.out.println("Binding registry...");
        // bind stub to registry
        registry.bind(refName, stub);
    }

}
