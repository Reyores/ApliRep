package com.projet.aplirep;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.rmi.*;

public class Magasin extends UnicastRemoteObject implements InterMagasin {

    List<Article> lArticle;

    public Magasin(List<Article> lA) throws RemoteException {
        super();
        lArticle=lA;
    }

    @Override
    public List<Article> afficherArticles() {
        return null;
    }

    @Override
    public boolean effectuerPaiement() {
        return false;
    }
}
