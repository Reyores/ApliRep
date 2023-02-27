package com.magasin;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BanqueInterface extends Remote
{
    boolean estSolvable(String id, double sommeAPayer) throws RemoteException;

}
