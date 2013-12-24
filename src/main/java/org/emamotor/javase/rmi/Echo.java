package org.emamotor.javase.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author tanabe
 */
public interface Echo extends Remote {
    String echo(String s) throws RemoteException;
}
