/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Event;
import Services.EventService;
import Services.MyListener;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author alaak
 */
public class EventBoxController implements Initializable {

    @FXML
    private Button participateButton;
    @FXML
    private Button sentBoolValue;
    @FXML
    private ImageView img;
    @FXML
    private Label eventNameLabel;
    @FXML
    private Label dateLabel;
    
    private Event event;
    private MyListener myListener;
    
    @FXML
    private void click(MouseEvent mouseEvent){
        myListener.onClickListener(event);
    }
    
   public void setData(Event event,MyListener myListener){
       this.event=event;
      this.myListener=myListener;
       eventNameLabel.setText(event.getEvent_name());
       dateLabel.setText("Date: "+event.getEvent_date()+" ---> "+event.getEvent_dateF());
       Image image =new Image(getClass().getResourceAsStream(event.getEvent_image()));
       img.setImage(image);
   }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void participateAction(ActionEvent event) {
      
        sendEmail();
        
        
    }
    public void sendEmail(){
        String from="kanzari.mohamed@esprit.tn";
        String to="alaakanzari@gmail.com";
        String host="smtp.gmail.com";
        final String username=from;
        final String password="91523291aA";
        
       //setup mail sever
       
       Properties props =System.getProperties();
       props.put("mail.smtp.auth","true");
       props.put("mail.smtp.starttls.enable","true");
       props.put("mail.smtp.host",host);
       props.put("mail.smtp.port","587");
       
       Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator(){
           @Override
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(username,password);
           }
           
       });
       try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("Notification de Participation");
            m.setText("Vous êtes admis");
            m.setFileName("C:\\xampp\\htdocs\\img");

            //send mail

            Transport.send(m);
            participateButton.setVisible(false);
            sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

       
       
}

    @FXML
    private void notif(MouseEvent event) {
        // Image img =new Image("https://www.vhv.rs/viewpic/howJhho_tick-black-png-icons-orange-check-mark-png/#");
        Notifications notificationBuilder =Notifications.create()
                .title("Participation ")
                .text("Avec Succès")
                .graphic(null)
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        System.out.println("Clicked on Notification");
                    }
                });
        
        notificationBuilder.showConfirm();
    }
}
