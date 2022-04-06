/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnShop;
    @FXML
    private Button btnService;
    @FXML
    private Button btnOpt;
    @FXML
    private Button btnContact;
    @FXML
    private Button btnAbout;
    @FXML
    private Button eventgo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goHome(ActionEvent event) {
    }

    @FXML
    private void goEvent(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("EventDescription.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            //scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goShop(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("shop2.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            //scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goService(ActionEvent event) {
    }

    @FXML
    private void goOpt(ActionEvent event) {
        
    }

    @FXML
    private void goAbout(ActionEvent event) {
    }
    
}
