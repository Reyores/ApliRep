package com.magasin;

import java.io.Serializable;

public class Article implements Serializable {

    String nom;
    double prix;
    String image;

    public Article(String nom,double prix,String image){
        this.nom=nom;
        this.prix=prix;
        this.image=image;
    }

    @Override
    public String toString() {
        return "Article{" +
                "nom='" + nom + '\'' +
                ", prix=" + prix +
                ", image='" + image + '\'' +
                '}';
    }
}
