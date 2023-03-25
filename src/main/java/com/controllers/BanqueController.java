package com.controllers;

import com.projet.aplirep.Banque;
import com.projet.aplirep.CompteBancaire;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BanqueController implements Initializable {
    @FXML
    VBox contentDisplay;

    public void loadComptes(Banque banque){
        List<CompteBancaire> lcb = banque.getAllComptes();
        contentDisplay.getChildren().clear();
        for(CompteBancaire cb : lcb){
            Label label = new Label();
            label.setText("Numéro de compte " + cb.getId() + ". Argent disponible : " + cb.getSomme() + " €"+"\n");
            contentDisplay.getChildren().add(label);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
