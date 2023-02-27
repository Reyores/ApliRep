package com.magasin;

import com.projet.aplirep.Panier;

import java.rmi.Remote;
import java.util.List;

public interface InterMagasin /*extends Remote */{

    List<Article> afficherArticles();

    boolean effectuerPaiement(String id, Panier panier);

}
