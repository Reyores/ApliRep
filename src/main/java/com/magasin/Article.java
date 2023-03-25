package com.magasin;

import java.io.Serializable;

public class Article implements Serializable {

    String nom;
    double prix;
    String image;

    int stock;

    public Article(String nom,double prix,String image, int stock){
        this.nom=nom;
        this.prix=prix;
        this.image=image;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Article{" +
                "nom='" + nom + '\'' +
                ", prix=" + prix +
                ", image='" + image + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public int getStock() {
        return stock;
    }

    public void retirerStock(int nb){
        stock = stock - nb;
    }
}
