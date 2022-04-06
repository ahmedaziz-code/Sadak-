/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Connection.MyConnection;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serviceinfo
 */
public class UserService {
   
     public Connection cnx;
    public PreparedStatement ste;
    
    public UserService(){
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void ajouterUtilisateur(User u){
        
        try {
            ste = cnx.prepareStatement("insert into utilisateur (username,nom_u,prenom_u,email_u,mdp_u,type_u)"+"value(?,?,?,?,?,?)");
            ste.setString(1, u.getUsername());
            ste.setString(2, u.getNom_u());
            ste.setString(3, u.getPrenom_u());
            ste.setString(4, u.getEmail_u());
            ste.setString(5, u.getMdp_u());
            ste.setString(6, u.getType_u());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    public void supprimerUser(String username){
        try {
            ste = cnx.prepareStatement("delete * from user where username=?");
            ste.setString(1, username);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    
    public void modifierUser(String username){
        try {
            ste = cnx.prepareStatement("update user set username=?, nom_u=?, prenom_u=?, email_u=?, mdp_u=?, type_u=? where username=?");
            ste.setString(1, username);
            ste.setString(2, username);
            ste.setString(3, username);
            ste.setString(4, username);
            ste.setString(5, username);
            ste.setString(6, username);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void rechercherUser(){
        
        try {
            List<User>utilisateurs = new ArrayList<>();
            ste = cnx.prepareStatement("select * from utilisateur where username=?");
            ResultSet rs = ste.executeQuery();
            User u = new User();
            while(rs.next()){
                u.setUsername(rs.getString("username"));
                u.setNom_u(rs.getString("nom_u"));
                u.setPrenom_u(rs.getString("prenom_u"));
                u.setEmail_u(rs.getString("email_u"));
                u.setMdp_u(rs.getString("mdp_u"));
                u.setType_u(rs.getString("type_u"));
                utilisateurs.add(u);
            }
            System.out.println(utilisateurs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
