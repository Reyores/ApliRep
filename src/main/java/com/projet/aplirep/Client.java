package com.projet.aplirep;

import com.magasin.Article;
import com.magasin.InterMagasin;

import java.util.List;

public class Client {

    String nom, prenom, email;
    Adresse adresse;
    Panier panier;
    InterMagasin magasin;

    public Client(String nom, String prenom, String email, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.panier = new Panier();
        this.magasin = null;
    }

    public void connexionMagasin(InterMagasin mag){
        magasin=mag;

        List<Article> lArticles=mag.afficherArticles();

        //afficher les articles dans l'IG

    }

    public void payer(){

    }

    public void ajouterArticle(Article a, int quantite){
        panier.ajouterArticle(a,quantite);
    }

    public void retirerArticle(Article a, int quantite){
        panier.retirerArticle(a,quantite);
    }


}
