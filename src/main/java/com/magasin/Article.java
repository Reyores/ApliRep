package com.magasin;

import java.io.Serializable;

public class Article implements Serializable {

    String nom;
    double prix;
    String image;

    public Article(String n,double p,String i){
        nom=n;
        prix=p;
        image=i;
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
