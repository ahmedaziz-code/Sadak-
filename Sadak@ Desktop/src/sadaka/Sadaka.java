// بسم الله الرحمن الرحيم 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sadaka;
import java.io.IOException;
import javafx.application.Application;
import javafx.beans.property.adapter.ReadOnlyJavaBeanDoublePropertyBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Entity.produit;
import Services.produit_service;


/**
 *
 * @author alaak
 */
public class Sadaka extends Application {

    private Stage primaryStage;
    private Parent parentPage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {


         this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Shop");

        //this.primaryStage.setTitle("ajoutproduit");
       
   
         //parentPage = FXMLLoader.load(getClass().getResource("/Gui/interface_vendeur.fxml"));
       // parentPage = FXMLLoader.load(getClass().getResource("/Gui/shop2.fxml"));


       // parentPage = FXMLLoader.load(getClass().getResource("/Gui/shop2.fxml"));
       // parentPage = FXMLLoader.load(getClass().getResource("/Gui/EventMangementVL.fxml"));

        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
    
    public static void main(String[] args) {
        
        launch (args) ;
        
    }
    
}
