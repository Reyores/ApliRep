package com.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Acceuil extends Application {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public Button btnDecathlon;

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
        String nomMagasin = btnDecathlon.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Magasin.fxml"));
        root = loader.load();

        MagasinController magasinController = loader.getController();

        magasinController.displayNomMagasin(nomMagasin);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
