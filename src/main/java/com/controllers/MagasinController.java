package com.controllers;

import com.magasin.Article;
import com.magasin.InterMagasin;
import com.magasin.Magasin;
import com.projet.aplirep.Banque;
import com.projet.aplirep.CompteBancaire;
import com.projet.aplirep.Panier;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

public class MagasinController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    private double IntTot = 0;

    @FXML
    public Label labelTotal;
    @FXML
    public Label labelMagasin;
    @FXML
    VBox contentPanier;

    @FXML
    VBox contentDisplay;

    Panier panierEnCours;

    {
        try
        {
            panierEnCours = new Panier();
        }
        catch (RemoteException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void afficherAccueil(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }






    public void loadArticles(List<Article> listArticles) throws FileNotFoundException
    {
        contentDisplay.getChildren().clear();



        for(Article a : listArticles)
        {

            Label labelName = new Label();
            Label labelPrice = new Label();
            Label labelStock = new Label();
            Button btnSelect = new Button();

            labelName.setStyle("-fx-font-size: 28px;");

            labelName.setText(a.getNom());
            labelPrice.setText("Prix : " +Double.toString(a.getPrice()) + " euros");
            labelStock.setText("Stock : " + Integer.toString(a.getStock()));
            btnSelect.setText("Ajouter au panier");
            btnSelect.setId(a.getNom());
            btnSelect.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    btnAjouterPanier(panierEnCours, a);
                }
            });

            contentDisplay.getChildren().add(labelName);
            contentDisplay.getChildren().add(labelPrice);
            contentDisplay.getChildren().add(labelStock);

            if(a.getStock() > 0)
                contentDisplay.getChildren().add(btnSelect);
        }
    }





    public void btnAjouterPanier(Panier panierEnCours, Article a)
    {
        panierEnCours.ajouterArticle(a, 1);
        Label labelNamePanier = new Label();
        Label labelPricePanier = new Label();
        Button btnRemove = new Button();

        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                btnRetirerPanier(panierEnCours, a, labelNamePanier, labelPricePanier, btnRemove);
            }
        });

        labelNamePanier.setText(a.getNom());
        labelPricePanier.setText(Double.toString(a.getPrice()));
        btnRemove.setText("Retirer du panier");

        contentPanier.getChildren().add(labelNamePanier);
        contentPanier.getChildren().add(labelPricePanier);
        contentPanier.getChildren().add(btnRemove);

        IntTot += a.getPrice();

        labelTotal.setText(Double.toString(IntTot) + " euros");
    }

    public void btnRetirerPanier(Panier panierEnCours, Article a, Label labelNamePanier, Label labelPricePanier, Button btnRemove)
    {
        panierEnCours.retirerArticle(a, 1);
        contentPanier.getChildren().remove(labelNamePanier);
        contentPanier.getChildren().remove(labelPricePanier);
        contentPanier.getChildren().remove(btnRemove);

        IntTot -= a.getPrice();

        labelTotal.setText(Double.toString(IntTot) + " euros");
    }







    public void payerPanier(ActionEvent event)
    {

        try
        {
           // Article baguette=new Article("Baguette",0.99,"baguette.png",30);
          //  Panier panier1 = new Panier();
           // panier1.ajouterArticle(baguette,1);

            Registry registry = LocateRegistry.getRegistry("localhost", 4330);
            InterMagasin magasininter = (InterMagasin) registry.lookup(labelMagasin.getText());
            System.out.println(magasininter.effectuerPaiement("1234", panierEnCours));
        }
        catch(Exception e)
        {
            System.out.println("Magasin serveur échec : " + e);
        }
    }



    public void displayNomMagasin(String nomMagasin)
    {
        labelMagasin.setText(nomMagasin);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}
