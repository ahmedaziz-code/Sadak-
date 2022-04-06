/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author pc
 */
public class Javamail { 
     public static void  sendMail(String recepient ) throws AddressException, MessagingException {
     String to = "sami.riahi@esprit.tn";
    String subject = "  Facture From Software-Connoisseur ";
    String msg ="Votre commande est validèe , merci pour votre don ! ";
    final String from ="sami.riahi@esprit.tn";
    final  String password ="SAmi191199*";


    Properties props = new Properties();  
    props.setProperty("mail.transport.protocol", "smtp");     
    props.setProperty("mail.host", "smtp.gmail.com");  
    props.put("mail.smtp.auth", "true");  
    props.put("mail.smtp.port", "465");  
    props.put("mail.debug", "true");  
    props.put("mail.smtp.socketFactory.port", "465");  
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
    props.put("mail.smtp.socketFactory.fallback", "false");  
    Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {
       protected PasswordAuthentication getPasswordAuthentication() {  
       return new PasswordAuthentication(from,password);  
   }  
   });  

   //session.setDebug(true);  
   Transport transport = session.getTransport();  
   InternetAddress addressFrom = new InternetAddress(from);  

   MimeMessage message = new MimeMessage(session);  
   message.setSender(addressFrom);  
   message.setSubject(subject);  
   message.setContent(msg, "text/plain");  
   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

   
   
   transport.connect();  
   Transport.send(message);  
   transport.close();
   }  
     
     
    
 
}


