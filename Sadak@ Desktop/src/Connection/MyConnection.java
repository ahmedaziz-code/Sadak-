/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class MyConnection {
    
    public String URL="jdbc:mysql://localhost/sadak@" , User="root" , Password="";
    public Connection cnx;
    public static MyConnection ct;
    
    private MyConnection(){
        try {
            cnx = DriverManager.getConnection(URL, User, Password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConnection(){
        return cnx;
    }
    
    public static MyConnection getInstance(){
        if(ct==null)
            ct = new MyConnection();
        return ct;
    }

//    /*public void seInt(int i, int ref_produit) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public void setString(int i, String nom_produit) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public void setFloat(int i, float prix_produit) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public void setInt(int i, int quantite_produit) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }*/
    public void executeUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
