/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Connection.MyConnection;
import Entity.Event;
import Services.EventService;
import com.jfoenix.controls.JFXDatePicker;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author alaak
 */
public class ModEventController implements Initializable {

    @FXML
    private TextField txtpath;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEname;
    @FXML
    private TextField txtdate;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button btnimage;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnAjouter1;

    /**
     * Initializes the controller class.
     */
    String i="";
     int eventId;
    @FXML
    private TextField txtdateF;
    @FXML
    private JFXDatePicker datePickerF;
        public File getfile() {
        return file;}
    
     private FileChooser fileChooser;
    private File file;
    public Stage primaryStage;
    private Desktop desktop= Desktop.getDesktop();
     private Image myImage;
     public Connection cnx;
     public Image imagee; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   // public EventManagementAdminController re=new EventManagementAdminController();
        
    @FXML
    private void close(MouseEvent event) {
        //cnx = MyConnection.getInstance().getConnection();
        //re.refreshTable();
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void setDate(ActionEvent event) {
         String Date=String.valueOf(datePicker.getValue());
        txtdate.setText(Date);
    }

    @FXML
    private void ImageChooser(ActionEvent event) {
        FileChooser imageChooser = new FileChooser();
       
        imageChooser.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif")
       );
        file=imageChooser.showOpenDialog(primaryStage);
        if(file!=null){
            //desktop.open(file);
            //textAreaImage.setText(file.getAbsolutePath());
           // txtpath.setText(file.getAbsolutePath());
            myImage = new Image(file.toURI().toString());
            imageView.setImage(myImage);
           // System.out.println(file.toURI().toString());
        }
        i=i+"a";
       
       String a=file.getAbsolutePath();
          if  (a.endsWith("png")){
              
              a="C:\\xampp\\htdocs\\img\\"+i+".png";
              
          }  else if (file.getAbsolutePath().endsWith("jpg")){
              
              a="C:\\xampp\\htdocs\\img\\"+i+".jpg";
              
        }else {
               
              a="C:\\xampp\\htdocs\\img\\"+i+".gif";
             
                }
      File fil=new File(a);
   txtpath.setText(fil.getAbsolutePath());
       // System.out.println(a);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(myImage, null), "png", fil);
        } catch (IOException ex) {
            Logger.getLogger(EventManagementVLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    
    }

   

    @FXML
    private void SaveEvent(ActionEvent event) {
         // String Name=txtName.getText();
        String Date=String.valueOf(datePicker.getValue());
        String Ename=txtEname.getText();
        String name=txtName.getText();
        String DateF=String.valueOf(datePickerF.getValue());
        String Description=txtDescription.getText();
       //  Event e=new Event(Name,Date,Description,fis);
       
      String Path=txtpath.getText();
        
        if ( Date.isEmpty() || Ename.isEmpty() ||Description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            
            alert.setHeaderText(null);
            alert.setContentText("Remplir les donnés nécessaires s'il vous plaît");
            alert.showAndWait();

        } else{
            Event e=new Event(eventId,name,Ename,Date,DateF,Description,Path);
            EventService es=new EventService();
            es.modifierEvent(e);
            Alert alertt = new Alert(Alert.AlertType.CONFIRMATION);
            alertt.setHeaderText(null);
            alertt.setContentText("Sahit");
            alertt.showAndWait();
        }
        
        
    }

    

    @FXML
    private void clean(ActionEvent event) {
        txtDescription.setText(null);
        txtEname.setText(null);
        imageView.setImage(null);
        txtdate.setText(null);
        
    }
    void setTextField(int id,String org,String name,String date,String dateF,String image,String desc){
        eventId=id;
        txtName.setText(org);
        txtEname.setText(name);
       imagee=new Image("file:"+image);
       imageView.setImage(imagee);
        txtdate.setText(date);
        txtDescription.setText(desc);
        txtpath.setText(image);
        txtdateF.setText(dateF);
    }

   

    

    @FXML
    private void setDateF(ActionEvent event) {
        
        String DateF=String.valueOf(datePickerF.getValue());
        txtdateF.setText(DateF);
    }

   
    
}
