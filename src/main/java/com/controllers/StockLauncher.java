package com.controllers;

import com.magasin.Article;
import com.magasin.InterMagasin;
import com.magasin.Magasin;
import com.projet.aplirep.Panier;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class StockLauncher extends Application {

    static StockController stockController;
    static Magasin decathon;
    static Magasin boulanger;

    @Override
    public void start(Stage stage) throws Exception {
        try{
            int port=4330;
            Registry reg = LocateRegistry.createRegistry(port);

            Article velo=new Article("VELO VTT RANDONNEE",6,"velo.png",12);
            Article but=new Article("BUT DE FOOTBALL",150,"but_footabll.png",5);
            Article tapis=new Article("TAPIS DE GYM",22,"tapis_gym.png",0);
            Article rack=new Article("RACK DE MUSCULATION", 300, "rack_muscu.png",9);

            ArrayList<Article> lArticlesDecathon=new ArrayList<>();
            lArticlesDecathon.add(velo);
            lArticlesDecathon.add(but);
            lArticlesDecathon.add(tapis);
            lArticlesDecathon.add(rack);

            //lancement Decathon
            decathon = new Magasin(lArticlesDecathon);
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
            boulanger = new Magasin(lArticlesBoulanger);
            InterMagasin boulangerInter = boulanger;
            reg.rebind("boulanger", boulangerInter);

            System.out.println("Fonctionnel");


           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Stock.fxml"));
           Parent root = fxmlLoader.load();

           stockController = fxmlLoader.getController();

           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();

           stockController.loadStock(decathon,boulanger);






        } catch (RemoteException e){
            System.out.println("Magasin serveur échec : " + e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateAffichage(){
        Platform.runLater(()-> {stockController.loadStock(decathon,boulanger);});
    }


}
