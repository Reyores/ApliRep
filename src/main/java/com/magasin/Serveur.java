package com.magasin;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Serveur {

    public static void main(String[] args) {
        System.out.println("Lancement serveurs Magasin");

        int port=4330;

        try
        {
            Registry reg = LocateRegistry.createRegistry(port);
            //magasin Decathon
            //preparation articles Decathon
            Article velo=new Article("VELO VTT RANDONNEE",360,"velo.png",12);
            Article but=new Article("BUT DE FOOTBALL",150,"but_footabll.png",5);
            Article tapis=new Article("TAPIS DE GYM",22,"tapis_gym.png",0);
            Article rack=new Article("RACK DE MUSCULATION", 300, "rack_muscu.png",9);

            ArrayList<Article> lArticlesDecathon=new ArrayList<>();
            lArticlesDecathon.add(velo);
            lArticlesDecathon.add(but);
            lArticlesDecathon.add(tapis);
            lArticlesDecathon.add(rack);

            //lancement Decathon
            Magasin decathon = new Magasin(lArticlesDecathon);
            InterMagasin decathonInter = decathon;
            reg.rebind("decathon", decathonInter);

            System.out.println("Decathon démaré");

        //magasin Boulanger
            //preparation articles Boulanger
            Article baguette=new Article("Baguette",0.99,"baguette.png",30);
            Article croissant=new Article("Croissant",1.20,"croissant.png",25);
            Article painChocolat=new Article("Pain au chocolat",1.40,"pain_chocolat.png",18);
            Article tarteCitron=new Article("Tarte au citron meringuee", 5.90, "tarte_citron.png",5);
            Article tarteletteFraise=new Article("Tartelette aux fraises", 1.90, "tartelette_fraises.png",9);

            ArrayList<Article> lArticlesBoulanger=new ArrayList<>();
            lArticlesBoulanger.add(baguette);
            lArticlesBoulanger.add(croissant);
            lArticlesBoulanger.add(painChocolat);
            lArticlesBoulanger.add(tarteCitron);
            lArticlesBoulanger.add(tarteletteFraise);

            //lancement Boulanger
            Magasin boulanger = new Magasin(lArticlesBoulanger);
            InterMagasin boulangerInter = boulanger;
            reg.rebind("boulanger", boulangerInter);

            System.out.println("Boulanger démaré");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
