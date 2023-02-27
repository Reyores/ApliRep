package com.magasin;

import com.projet.aplirep.Panier;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Article article1 = new Article("Article1", 22, "image");
        Article article2 = new Article("Article2", 15, "image2");

        List<Article> list = new ArrayList<Article>();
        list.add(article1);
        list.add(article2);

        try
        {
            Magasin magasin1 = new Magasin(list);

            List<Article> listAffiche = magasin1.afficherArticles();

            Panier panier1 = new Panier();

            panier1.ajouterArticle(article1, 3);
            panier1.ajouterArticle(article2, 2);

            System.out.println(magasin1.retournerSommeArticles(panier1));



        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }


    }
}
