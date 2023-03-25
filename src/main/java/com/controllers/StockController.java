package com.controllers;

import com.magasin.Article;
import com.magasin.Magasin;
import com.projet.aplirep.Banque;
import com.projet.aplirep.CompteBancaire;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StockController implements Initializable {
    @FXML
    VBox contentDisplay1;

    @FXML
    VBox contentDisplay2;

    public void loadStock(Magasin mag1, Magasin mag2){
        List<Article> la1 = mag1.afficherArticles();
        List<Article> la2 = mag2.afficherArticles();
        contentDisplay1.getChildren().clear();
        contentDisplay2.getChildren().clear();
        for(Article article : la1){
            Label label = new Label();
            label.setText("Article : " + article.getNom() + ". Stock disponible : " + article.getStock());
            contentDisplay1.getChildren().add(label);
        }

        for(Article article : la2){
            Label label = new Label();
            label.setText("Article : " + article.getNom() + ". Stock disponible : " + article.getStock());
            contentDisplay2.getChildren().add(label);

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
