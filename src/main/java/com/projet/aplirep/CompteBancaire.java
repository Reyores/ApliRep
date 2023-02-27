package com.projet.aplirep;

public class CompteBancaire {

    String id;
    double somme;

    public CompteBancaire(String id){
        this.id=id;
        this.somme=0;
    }

    public CompteBancaire(String id, double sommeInitiale) {
        this.id = id;
        this.somme = sommeInitiale;
    }

    public String getId() {
        return id;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }
}
