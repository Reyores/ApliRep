package com.magasin;

import com.projet.aplirep.Panier;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface InterMagasin extends Remote {

    List<Article> afficherArticles() throws RemoteException;

    boolean effectuerPaiement(String id, Panier panier) throws RemoteException;

}
