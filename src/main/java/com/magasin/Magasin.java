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
        lArticle = lA;
    }

    /**
     * constructeur par defaut
     *
     * @throws RemoteException
     */
    public Magasin() throws RemoteException {
        super();
        lArticle = new ArrayList<>();
    }

    /**
     * @return - Articles d'un magasin
     */
    @Override
    public List<Article> afficherArticles() {
        return lArticle;
    }

    /**
     * @return - Retourne la validité du payement
     */
    @Override
    public boolean effectuerPaiement(String id, HashMap<Article, Integer> panier) throws RemoteException {

        double prix = retournerSommeArticles(panier);
        boolean success = false;

        try {

            Registry registry = LocateRegistry.getRegistry("localhost", 3330);
            BanqueInterface banqueint = (BanqueInterface) registry.lookup("banque");
            success = banqueint.estSolvable(id, prix);
        } catch (Exception e) {
            System.out.println("erreur aaf" + e.getMessage());
        }

        if (success) {
            Iterator iterator = panier.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry mapentry = (Map.Entry) iterator.next();
                System.out.println(mapentry.getKey());
                Article article = (Article) mapentry.getKey();
                int idx = getidx(article);
                lArticle.get(idx).retirerStock((Integer) mapentry.getValue());
                System.out.println("test");
                System.out.println("stock ! " + lArticle.get(idx).getStock());
                StockLauncher.updateAffichage();
            }
            System.out.println("cc bg");
        }

        System.out.println("ALORS ?" + success);
        return success;
    }

    /**
     * @param article - Ajoute un article au magasin
     */
    public void ajouterArticle(Article article) {
        lArticle.add(article);
    }

    /**
     * @param article - Retire un article magasin
     */
    public void retirerArticle(Article article) {
        lArticle.remove(article);
    }

    /**
     * @param panier - Récupère le panier
     * @return - Retourne la somme totale du panier
     */
    public double retournerSommeArticles(HashMap<Article, Integer> panier) {
        HashMap<Article, Integer> articlesPris = panier;
        System.out.println("cc");
        double i = 0;

        for (Map.Entry mapentry : articlesPris.entrySet()) {
            Article article = (Article) mapentry.getKey();
            Integer nbArticles = (Integer) mapentry.getValue();

            i += article.prix * nbArticles;
        }

        return i;
    }

    public int getidx(Article article) {
        int idx = 0;
        boolean found = false;
        System.out.println(lArticle.size());
        System.out.println("yto");
        while (idx < lArticle.size() && !found) {
            System.out.println("test");
            if (!lArticle.get(idx).getNom().equalsIgnoreCase(article.getNom())) {
                System.out.println(lArticle.get(idx).getNom() + " " + article.getNom());
                idx++;
            } else {
                found = true;
            }
        }
        System.out.println(idx);
        return idx;
    }

}
