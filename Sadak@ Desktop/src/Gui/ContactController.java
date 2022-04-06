/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * FXML Controller class
 *
 * @author alaak
 */
public class ContactController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField messageField;
    @FXML
    private Label sentBoolValue;
    @FXML
    private TextField emailField;
    @FXML
    private Button sendEmailButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void buttonClicked(ActionEvent Event){
        sendEmail();
    }
    public void sendEmail(){
        String from="kanzari.mohamed@esprit.tn";
        String to=emailField.getText();
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
            m.setSubject(emailField.getText());
            m.setText(messageField.getText());
            
//           MimeBodyPart messageBodyPart = new MimeBodyPart(); 
//           messageBodyPart.setContent("<h1>Assalamou Aalaikom 😊</h1>", "text/html");
//            //send mail
//            MimeBodyPart attachment = new MimeBodyPart(); 
//            try { 
//                attachment.attachFile(new File("FILE_TO_SEND"));
//            } catch (IOException ex) {
//                Logger.getLogger(ContactController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            MimeBodyPart attachment2 = new MimeBodyPart(); 
//            try { 
//                attachment2.attachFile(new File("FILE_TO_SEND"));
//            } catch (IOException ex) {
//                Logger.getLogger(ContactController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            MimeMultipart multipart = new MimeMultipart(); 
//            multipart.addBodyPart(messageBodyPart); 
//            multipart.addBodyPart(attachment); 
//            multipart.addBodyPart(attachment2);
//            m.setContent(multipart);
//            
            Transport.send(m);
            sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

       
       
}
}
