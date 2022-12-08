package com.projet.aplirep;

import java.util.List;

public class Magasin implements InterMagasin{

    List<Article> lArticle;

    public Magasin(List<Article> lA){
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
