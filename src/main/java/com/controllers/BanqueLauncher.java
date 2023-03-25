package com.controllers;

import com.projet.aplirep.Banque;
import com.projet.aplirep.BanqueInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BanqueLauncher extends Application {
    static Banque banque;

    static BanqueController banqueController;

    @Override
    public void start(Stage stage) throws Exception {
        try{
            int port = 3330;
            Registry reg = LocateRegistry.createRegistry(port);
            banque = new Banque();
            BanqueInterface banqueinter = banque;
            reg.rebind("banque", banqueinter);
            System.out.println("Fonctionnel");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Banque.fxml"));
            Parent root = fxmlLoader.load();

            banqueController = fxmlLoader.getController();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            banqueController.loadComptes(banque);

        } catch (RemoteException e){
            System.out.println("Banque serveur échec : " + e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateAffichage(){
        banqueController.loadComptes(banque);
    }


}
