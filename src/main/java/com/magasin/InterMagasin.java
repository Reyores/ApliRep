package com.magasin;

import java.rmi.Remote;
import java.util.List;

public interface InterMagasin extends Remote {

    List<Article> afficherArticles();

    boolean effectuerPaiement();

}
