/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Connection.MyConnection;
import Entity.Event;
import Services.MyListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author alaak
 */
public class EventDescriptionController implements Initializable {

    @FXML
    private VBox chosenEventCard;
    @FXML
    private ImageView eventImg;
    @FXML
    private Label dateLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    private MyListener myListener;
    private List<Event>  events=new ArrayList<>();
   
    private List<Event> getData(){
        
        try {
            List<Event> events=new ArrayList<>();
            Event event;
            
            Connection cnx = MyConnection.getInstance().getConnection();
            
            ResultSet rs=cnx.createStatement().executeQuery("select * from news");
            while (rs.next()){
                
                
                event=new Event();
                event.setEvent_date(rs.getString("event_date"));
                event.setEvent_dateF(rs.getString("event_dateF"));
                event.setEvent_content(rs.getString("event_content"));
                event.setEvent_image(rs.getString("event_image"));
                event.setEvent_organiser(rs.getString("event_organiser"));
                events.add(event);
            }
            
            event=new Event();
            event.setEvent_date("2021-10");
            event.setEvent_dateF("2021-105");
            event.setEvent_content("tesqdqdsdqqqsddsqdsqdsqsdt");
            event.setEvent_image("/Images/mango.png");
            event.setEvent_organiser("Mohammed");
            events.add(event);
            event=new Event();
            event.setEvent_date("2021-10");
            event.setEvent_dateF("2021-10");
            event.setEvent_content("test");
            event.setEvent_image("/Images/online-news-concept-looking-upcoming-events-web-page-idea-social-media-network-isolated-flat-vector-illustration-123154032.jpg");
            event.setEvent_organiser("Mohsdfammed");
            events.add(event);
            
            event=new Event();
            event.setEvent_date("2021-10");
            event.setEvent_dateF("2021-10");
            event.setEvent_content("test");
            event.setEvent_image("/Images/orange.png");
            event.setEvent_organiser("Mohammed");
            events.add(event);
            
            event=new Event();
            event.setEvent_date("2021-10");
            event.setEvent_dateF("2021-10");
            event.setEvent_content("test");
            event.setEvent_image("/Images/orange.png");
            event.setEvent_organiser("Mohammed");
            events.add(event);
            
            event=new Event();
            event.setEvent_date("2021-10");
            event.setEvent_dateF("2021-10");
            event.setEvent_content("test");
            event.setEvent_image("/Images/orange.png");
            event.setEvent_organiser("Mohammed");
            events.add(event);
            
            event=new Event();
            event.setEvent_date("2021-10");
            event.setEvent_dateF("2021-10");
            event.setEvent_content("testfddfffffffffffffffffffffff");
            event.setEvent_image("/Images/mango.png");
            event.setEvent_organiser("Mohammed");
            events.add(event);
            
        
        } catch (SQLException ex) {
            Logger.getLogger(EventDescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
            return events;
    }
    
    
    private void setChosenEvent(Event event){
        Image image =new Image(getClass().getResourceAsStream(event.getEvent_image()));
        eventImg.setImage(image);
        dateLabel.setText("Date  "+event.getEvent_date()+" ---> "+event.getEvent_dateF());
         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //events.addAll(getData());
        if(events.size()>0){
            setChosenEvent(events.get(0));
            myListener=new MyListener() {
                @Override
                public void onClickListener(Event event) {
                    setChosenEvent(event);
                }
            };
        }
        
        int column=0;
        int row=0;
        try {
        for (int i=0;i<events.size();i++){
                FXMLLoader fxmloader= new FXMLLoader();
                fxmloader.setLocation(getClass().getResource("/Gui/EventBox.fxml"));
                AnchorPane anchorPane=fxmloader.load();
                
                EventBoxController eventBoxController=fxmloader.getController();
                eventBoxController.setData(events.get(i),myListener);
                
                if (column==2){
                    column=0;
                    row++;
                }
                
                grid.add(anchorPane,column++,row);
                
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane,new Insets(10));
        
        }
            } catch (IOException ex) {
                Logger.getLogger(EventDescriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    

    
}
