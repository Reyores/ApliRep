package com.projet.aplirep;

import com.magasin.Article;

import java.util.List;

public class Panier {

    List<Article> lArticles;

    public void ajouterArticle(Article a){
        lArticles.add(a);
    }

    public void retirerArticle(Article a){
        lArticles.remove(a);
    }

}
