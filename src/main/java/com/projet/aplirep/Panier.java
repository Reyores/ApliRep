package com.projet.aplirep;

import com.controllers.StockLauncher;
import com.magasin.Article;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Panier extends UnicastRemoteObject {

    HashMap<Article,Integer> lArticles;

    public Panier() throws RemoteException {
        super();
        lArticles=new HashMap<>();
    }

    public void ajouterArticle(Article a, int quantite){
        lArticles.put(a,quantite);
    }

    public void modifierquantite(Article a, int q){
        lArticles.put(a,q);
    }

    public void retirerArticle(Article a, int quantite){
        lArticles.remove(a,quantite);
    }

    public void viderPanier(){
        lArticles = new HashMap<>();
    }

    public HashMap<Article,Integer> getPanier(){
        return lArticles;
    }




}
