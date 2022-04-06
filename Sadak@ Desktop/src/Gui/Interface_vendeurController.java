/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import  Entity.produit;
import Services.produit_service;
/**
 * FXML Controller class
 *
 * @author pc
 */
public class Interface_vendeurController implements Initializable {

    @FXML
    private TextField pvref;
    @FXML
    private TextField pvnom;
    @FXML
    private TextField pvprix;
    @FXML
    private TextField pvdate;
    @FXML
    private TextField pvquant;
    @FXML
    private TextField pvcat;
    @FXML
    private Button pvajout;
    @FXML
    private Button pvmodifier;
    @FXML
    private Button pvretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

//    @FXML
//    private void saveVproduit(ActionEvent event) {
//        String vnom=pvnom.getText();
//        float vprix =Float.parseFloat(pvprix.getText());
//        String vdate=pvdate.getText();
//        int vquantite=Integer.parseInt(pvquant.getText());
//        String vcat=pvcat.getText();
//       // produit v = new produit(0, vquantite, vnom,vcat, vdate,vprix);
//        produit_service pvs=new  produit_service();
//        pvs.ajouterProduit(v);
//        
//        
//        
//    }
//    
}
