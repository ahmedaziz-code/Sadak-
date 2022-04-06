
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui ;

import mylistener.mylistener;
import Entity.produit;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AfficherOPController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    @FXML
    private Label valido;
    @FXML
    private Button panier;
    
    
    private produit oeuvre;
    private mylistener myListener;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void ajouter(ActionEvent event) {
        myListener.onpressed(event,oeuvre);
    }

    @FXML
    private void click(MouseEvent event) {
         myListener.onClickListener(event,oeuvre);
    }
    
    public void setData(produit oeuvre, mylistener myListener) {
        this.oeuvre  = oeuvre;
        this.myListener = myListener;
        nameLabel.setText(oeuvre.getNom_produit());
        priceLable.setText((oeuvre.getPrix_produit())+"DT");
   
       img.setImage(new Image("/Images/"+ oeuvre.getImage()));
        img.setFitHeight(150);
        img.setFitWidth(150); 
       
    } 
    
}