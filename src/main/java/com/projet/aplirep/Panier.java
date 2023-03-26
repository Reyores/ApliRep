package com.projet.aplirep;

import com.magasin.Article;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;

public class Panier extends UnicastRemoteObject {

    HashMap<Article,Integer> lArticles;

    public Panier() throws RemoteException {
        super();
        lArticles=new HashMap<>();
    }

    public void ajouterArticle(Article a, int quantite){
        lArticles.put(a,quantite);
    }

    public void retirerArticle(Article a, int quantite){
        lArticles.remove(a,quantite);
    }

    public HashMap<Article,Integer> getPanier(){
        return lArticles;
    }

}
