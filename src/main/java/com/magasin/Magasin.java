package com.magasin;

import com.projet.aplirep.BanqueInterface;
import com.projet.aplirep.Panier;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.HashMap;
import java.rmi.*;
import java.util.Map;

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
    public boolean effectuerPaiement(String id, Panier panier)
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

        }
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
