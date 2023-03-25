package com.projet.aplirep;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServeurBanque
{
    public static void main(String[] args)
    {
        try
        {
            int port = 3330;
            Registry reg = LocateRegistry.createRegistry(port);
            Banque banque = new Banque();
            BanqueInterface banqueinter = banque;
            reg.rebind("banque", banqueinter);
            System.out.println("Fonctionnel");
        }
        catch(Exception e)
        {
            System.out.println("Banque serveur échec : " + e.getMessage());
        }
    }
}
