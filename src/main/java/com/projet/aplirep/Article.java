package com.projet.aplirep;

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

}
