 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Entity.produit;
import Connection.MyConnection;
import static Connection.MyConnection.ct;
import mylistener.Iproduit;
/**
 *
 * @author pc
 */
public class produit_service implements Iproduit<produit>{
    Connection conx = MyConnection.getInstance().getConnection();

    
    
    @Override
    public List<produit> afficherLO() {
        
        String req="SELECT * FROM produit";
        List<produit> listO =new ArrayList<>();
        
        try {
           Statement ste = conx.createStatement();
        
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                produit o1 =new produit();
                o1.setRef_produit(rs.getInt("ref_produit"));
                o1.setUser_id(rs.getInt("user_id"));
                o1.setNom_produit(rs.getString("nom_produit"));
                o1.setCategorie(rs.getString("categorie"));
                o1.setPrix_produit(rs.getFloat("prix_produit"));
                o1.setQuantite_produit(rs.getInt("quantite_produit"));
                o1.setImage(rs.getString("image"));
                listO.add(o1);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(produit_service.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return listO;
    
    }

@Override
    public List<produit> displayAll() {
         String req="select * from produit";
            ObservableList<produit> list=FXCollections.observableArrayList();
       
            try {
           
            
            Statement ste = conx.createStatement();
            ResultSet rs =ste.executeQuery(req);
            while(rs.next()){
                produit o2 =new produit();
                o2.setRef_produit(rs.getInt("ref_produit"));
                o2.setUser_id(rs.getInt("user_id"));
                o2.setNom_produit(rs.getString("nom_produit"));
            o2.setCategorie(rs.getString("categorie"));
                o2.setPrix_produit(rs.getFloat("prix_produit"));
                o2.setQuantite_produit(rs.getInt("quantite_produit"));
                o2.setImage(rs.getString("image"));
                
           list.add(o2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(produit_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return list;
    
}
    
    
    public void ajouterProduit (produit p) { 
    
    
    
    
    
    }

 
}
