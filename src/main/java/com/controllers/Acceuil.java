package com.controllers;

import com.magasin.InterMagasin;
import com.magasin.Magasin;
import com.projet.aplirep.Banque;
import com.projet.aplirep.BanqueInterface;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Acceuil extends Application {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public Button btnDecathlon;

    @FXML
    public Button btnBoulangerie;


    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void afficherMagasin(ActionEvent event) throws IOException
    {
        boolean success = false;

        Button btnSelected = (Button) event.getSource();
        String nomMagasin = btnSelected.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Magasin.fxml"));
        root = loader.load();

        MagasinController magasinController = loader.getController();

        magasinController.displayNomMagasin(nomMagasin);
        magasinController.labelTotal.setText("0 euros");

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        try
        {
            Registry registry = LocateRegistry.getRegistry("localhost", 4330);
            InterMagasin magasininter = (InterMagasin) registry.lookup(nomMagasin);
            magasinController.loadArticles(magasininter.afficherArticles());
        }
        catch(Exception e)
        {
            System.out.println("Magasin serveur échec : " + e.getMessage());
        }


    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
