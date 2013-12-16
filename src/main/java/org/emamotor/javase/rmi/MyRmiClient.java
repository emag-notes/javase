package org.emamotor.javase.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author tanabe
 */
public class MyRmiClient {

    public static void main(String[] args) {
        String host = args[0];
        int port = 1099;

        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            Echo stub = (Echo) registry.lookup("Echo");
            String s = stub.echo("Hello RMI!");
            System.out.println(s);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
