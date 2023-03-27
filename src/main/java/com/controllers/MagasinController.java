package com.controllers;

import com.magasin.Article;
import com.magasin.InterMagasin;
import com.magasin.Magasin;
import com.projet.aplirep.Banque;
import com.projet.aplirep.BanqueInterface;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
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
    static MagasinController magasinController;

    private double IntTot = 0;
    static Magasin magasin;
    int quant = 0;
    int i = 0;

    @FXML
    private TextField inputID;
    @FXML
    private Button btnValiderPayement;
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
            ImageView img = new ImageView();

            labelName.setStyle("-fx-font-size: 28px;");

            Image image = new Image(new File(("target/classes/Produits/Images/"+a.getImage())).toURI().toString());
            img.setImage(image);
            img.setFitWidth(50);
            img.setFitHeight(50);

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
            contentDisplay.getChildren().add(img);
            contentDisplay.getChildren().add(labelStock);

            if(a.getStock() > 0)
                contentDisplay.getChildren().add(btnSelect);
        }
    }





    public void btnAjouterPanier(Panier panierEnCours, Article a)
    {
        Label labelNamePanier = new Label();
        Label labelPricePanier = new Label();
        Label labelQuantite = new Label();
        Button btnRemove = new Button();

        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                btnRetirerPanier(panierEnCours, a, labelNamePanier, labelPricePanier, btnRemove, labelQuantite);
            }
        });

        labelNamePanier.setText(a.getNom());
        labelPricePanier.setText(Double.toString(a.getPrice()));

        if(panierEnCours.getPanier().containsKey(a))
        {
            i = panierEnCours.getPanier().get(a);

            panierEnCours.modifierquantite(a, i+1);

            if(i <= 0)
            {
                btnRemove.setText("Retirer du panier");

                contentPanier.getChildren().add(labelNamePanier);
                contentPanier.getChildren().add(labelPricePanier);
                contentPanier.getChildren().add(labelQuantite);
                contentPanier.getChildren().add(btnRemove);
            }

        }
        else
        {
            panierEnCours.ajouterArticle(a, 1);

            btnRemove.setText("Retirer du panier");

            contentPanier.getChildren().add(labelNamePanier);
            contentPanier.getChildren().add(labelPricePanier);
            contentPanier.getChildren().add(labelQuantite);
            contentPanier.getChildren().add(btnRemove);
        }

        int b = panierEnCours.getPanier().get(a);
        labelQuantite.setText(Integer.toString(b));
        IntTot += a.getPrice();
        labelTotal.setText(Double.toString(IntTot) + " euros");
    }

    public void btnRetirerPanier(Panier panierEnCours, Article a, Label labelNamePanier, Label labelPricePanier, Button btnRemove, Label labelQuantite)
    {
        i = panierEnCours.getPanier().get(a);

        panierEnCours.modifierquantite(a, i-1);

        IntTot -= a.getPrice();
        labelTotal.setText(Double.toString(IntTot) + " euros");

        if(panierEnCours.getPanier().get(a) <= 0)
        {
            contentPanier.getChildren().remove(labelNamePanier);
            contentPanier.getChildren().remove(labelPricePanier);
            contentPanier.getChildren().remove(btnRemove);
            contentPanier.getChildren().remove(labelQuantite);
        }

    }

    public void payerPanier(ActionEvent event)
    {
        System.out.println(panierEnCours.getPanier());
        btnValiderPayement.setVisible(true);
        inputID.setVisible(true);
    }

    public void payerConfirmer(ActionEvent event)
    {
        try
        {
            System.out.println(" panier final " + panierEnCours.getPanier());
            Registry registry = LocateRegistry.getRegistry("localhost", 4330);
            InterMagasin magasininter = (InterMagasin) registry.lookup(labelMagasin.getText());

            System.out.println(magasininter.effectuerPaiement(inputID.getText(), panierEnCours.getPanier()));

            if(magasininter.effectuerPaiement(inputID.getText(), panierEnCours.getPanier()))
            {
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setContentText("Achat réussi");
                b.show();
            }
            else
            {
                Alert b = new Alert(Alert.AlertType.INFORMATION);
                b.setContentText("Achat raté");
                b.show();
            }

            contentPanier.getChildren().clear();
            panierEnCours.viderPanier();
            labelTotal.setText("0 euros");
            btnValiderPayement.setVisible(false);
            inputID.setVisible(false);

        }
        catch(RemoteException e)
        {
            System.out.println("Magasin serveur échec : " + e);
        } catch (NotBoundException e) {
            System.out.println("Magasin serveur échec out of bound : " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
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
