package com.projet.aplirep;

public class Client {

    String nom, prenom, email;
    Adresse adresse;
    Panier panier;
    InterMagasin magasin;

    public Client(String nom, String prenom, String email, Adresse adresse, Panier panier, InterMagasin magasin) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.panier = panier;
        this.magasin = magasin;
    }

    void connexionMagasin(InterMagasin mag){

    }

    void payer(){

    }


}
