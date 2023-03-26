package com.magasin;

import com.controllers.StockLauncher;
import com.projet.aplirep.BanqueInterface;
import com.projet.aplirep.Panier;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.rmi.*;

public class Magasin extends UnicastRemoteObject implements InterMagasin {

    List<Article> lArticle;

    /**
     * @param lA
     * @throws RemoteException
     */
    public Magasin(List<Article> lA) throws RemoteException {
        super();
        lArticle=lA;
    }

    /**
     * constructeur par defaut
     * @throws RemoteException
     */
    public Magasin() throws RemoteException {
        super();
        lArticle=new ArrayList<>();
    }

    /**
     @return - Articles d'un magasin
     */
    @Override
    public List<Article> afficherArticles()
    {
        return lArticle;
    }

    /**
     @return - Retourne la validité du payement
     */
    @Override
    public boolean effectuerPaiement(String id, Panier panier) throws RemoteException
    {

        double prix = retournerSommeArticles(panier);
        boolean success = false;

        try
        {

            Registry registry = LocateRegistry.getRegistry("localhost", 3330);
            BanqueInterface banqueint = (BanqueInterface) registry.lookup("banque");
            success = banqueint.estSolvable(id, prix);
        }
        catch (Exception e)
        {
            System.out.println("erreur aaf" + e.getMessage());
        }

        if(success){
            Iterator iterator = panier.getPanier().entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry mapentry = (Map.Entry) iterator.next();
                int idx = lArticle.indexOf(mapentry.getKey());
                lArticle.get(idx).retirerStock((Integer) mapentry.getValue());
                System.out.println("stock ! " +lArticle.get(idx).getStock());
                StockLauncher.updateAffichage();
            }
        }

        System.out.println("ALORS ?" + success);
        return success;
    }

    /**
      @param article - Ajoute un article au magasin
     */
    public void ajouterArticle(Article article)
    {
        lArticle.add(article);
    }

    /**
     * @param article - Retire un article magasin
     */
    public void retirerArticle(Article article)
    {
        lArticle.remove(article);
    }

    /**
     * @param panier - Récupère le panier
     * @return - Retourne la somme totale du panier
     */
    public double retournerSommeArticles(Panier panier)
    {
        HashMap<Article, Integer> articlesPris = panier.getPanier();
        double i = 0;

        for (Map.Entry mapentry : articlesPris.entrySet())
        {
            Article article = (Article) mapentry.getKey();
            Integer nbArticles = (Integer) mapentry.getValue();

            i += article.prix * nbArticles;
        }

        return i;
    }
}
