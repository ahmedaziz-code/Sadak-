/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Connection.MyConnection;
import Entity.Famille;
import Entity.User;
import com.pdfjet.A4;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import static javax.print.DocFlavor.BYTE_ARRAY.PDF;

/**
 *
 * @author ahmed
 */
public class FamilleService {
    
    public Connection cnx;
    public PreparedStatement ste;
    
    public FamilleService(){
        cnx = MyConnection.getInstance().getConnection();
        System.out.println("Cnx établie");
    }
    
    public void ajouterFamille(Famille f){
        try {
            String sql = "insert into Famille(pays,etat_f, ville_f, nom_p"
                    + ", prenom_p, tele_p, d_naissance_p, s_revenue_p"
                    + ", nom_m, prenom_m, tele_m,d_naissance_m, s_revenue_m"
                    + ", r_brute_f, nbr_enf, nbr_enf_malade, besoin_f,verif_f, remarque, dateV)"
                    + ""+"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ste = cnx.prepareStatement(sql);
            ste.setString(1, f.getPays_f());
            ste.setString(2, f.getEtat_f());
            ste.setString(3, f.getVille_f());
            
            ste.setString(4, f.getNom_p());
            ste.setString(5, f.getPrenom_p());
            ste.setInt(6, f.getTele_p());
            ste.setString(7, f.getD_naissace_p());
            ste.setString(8, f.getS_revenue_p());
            ste.setString(9, f.getNom_m());
            ste.setString(10, f.getPrenom_m());
            ste.setInt(11, f.getTele_m());
            ste.setString(12, f.getD_naissance_m());
            ste.setString(13, f.getS_revenue_m());
            ste.setFloat(14, f.getR_brut_f());
            ste.setInt(15, f.getNbr_enf());
            ste.setInt(16, f.getNbr_enf_malade());
            ste.setString(17, f.getBesoin_f());
            ste.setString(18, f.getVerif_f());
            ste.setString(19, f.getRemarque());
            ste.setString(20, f.getDate_v());
            ste.executeUpdate();
            System.out.println("Famille ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierFaVe(int id_f){
        try {
            String sql = "update famille set verif_f='"+"Demande Verification"+"' where id_f='"+id_f+"'";
            ste = cnx.prepareStatement(sql);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierFamille(int id_f,String pays_f,String etat_f, String ville_f
            ,String nom_p,String prenom_p,int tele_p,String d_naissance_p
            ,String s_revenue_p,String nom_m,String prenom_m,int tele_m,String d_naissance_m
            ,String s_revenue_m,float r_brute_f,int nbr_enf,int nbr_enf_malade,String besoin_f
            ,String verif_f, String remarque, String date_v ){
        try {
            String sql = "update Famille set pays='"+pays_f+"',"+""+"etat_f='"+etat_f+"',"
                    + ""+" ville_f='"+ville_f+"',"+" nom_p='"+nom_p+"',"
                    + ""+" prenom_p='"+prenom_p+"',"+" tele_p='"+tele_p+"',"+" d_naissance_p='"+d_naissance_p+"',"
                    + ""+" s_revenue_p='"+s_revenue_p+"',"+" nom_m='"+nom_m+"',"+" prenom_m='"+prenom_m+"'"
                    + ","+" tele_m='"+tele_m+"',"+" d_naissance_m='"+d_naissance_m+"'"
                    + ","+" s_revenue_m='"+s_revenue_m+"',"+" r_brute_f='"+r_brute_f+"'"
                    + ","+" nbr_enf='"+nbr_enf+"',"+" nbr_enf_malade='"+nbr_enf_malade+"'"
                    + ","+" besoin_f='"+besoin_f+"',"+" verif_f='"+verif_f+"',"+"remarque='"+remarque+"'"
                    + ","+"dateV='"+date_v+"' where id_f='"+id_f+"'";
            ste = cnx .prepareStatement(sql);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimerFamille(int id_f){
        try {
            ste = cnx.prepareStatement("delete from Famille where id_f=?");
            ste.setInt(1, id_f);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Famille> afficherFamille(){
        
        List<Famille>familles = new ArrayList<>();
        
        try {
            
            ste = cnx.prepareStatement("select * from Famille ");
            ResultSet rs = ste.executeQuery();
            
            while(rs.next()){
                Famille f = new Famille();
                f.setId_f(rs.getInt("id_f"));
                f.setPays_f(rs.getString("pays_f"));
                f.setEtat_f(rs.getString("etat_f"));
                f.setVille_f(rs.getString("ville_f"));
                
                f.setNom_p(rs.getString("nom_p"));
                f.setPrenom_p(rs.getString("prenom_p"));
                f.setTele_p(rs.getInt("tele_p"));
                f.setD_naissace_p(rs.getString("d_naissance_p"));
                f.setS_revenue_p(rs.getString("s_revenue_p"));
                f.setNom_m(rs.getString("nom_m"));
                f.setPrenom_m(rs.getString("prenom_m"));
                f.setTele_m(rs.getInt("tele_m"));
                f.setD_naissance_m(rs.getString("d_naissance_m"));
                f.setS_revenue_m(rs.getString("s_revenue_m"));
                f.setR_brut_f(rs.getFloat("r_brute_f"));
                f.setNbr_enf(rs.getInt("nbr_enf"));
                f.setNbr_enf_malade(rs.getInt("nbr_enf_malade"));
                f.setBesoin_f(rs.getString("besoin_f"));
                f.setVerif_f(rs.getString("verif_f"));
                familles.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return familles;
        
    }
    
    public void sendEmail(User u, Famille f){
        String from="ahmedaziz.elj@esprit.tn";
        String to=u.getEmail_u();
        String host="smtp.gmail.com";
        final String username=from;
        final String password="chalbou9+";
        
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
            m.setSubject("Verification des familles");
            m.setText("Pouver vous verifier la famille "+f.getNom_p());
            
            // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("Pouver vous verifier la famille "+f.getNom_p());

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "/users/ahmed/Desktop/Software-Connoisseur/Sadak@/"+f.getNom_p()+".pdf";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         m.setContent(multipart);

            

            //send mail

            Transport.send(m);
           
            System.out.println("Message sent!");

        }   catch (MessagingException b){
            b.printStackTrace();
        }
    }

    public void modifierEtatFamille(int ID , String rmq , String time) {
        try {
            String sql = "update famille set verif_f='"+"Verifiee"+"', remarque='"+rmq+"',"
                    + "dateV='"+time+"' where id_f='"+ID+"'";
            ste = cnx.prepareStatement(sql);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }
    
    
    
}
