package com.projet.aplirep;

import com.magasin.Article;

import java.util.HashMap;
import java.util.List;

public class Panier {

    HashMap<Article,Integer> lArticles;

    public Panier(){
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
