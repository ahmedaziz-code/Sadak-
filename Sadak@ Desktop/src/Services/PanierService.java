/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Connection.MyConnection;
import Entity.Element_panier;
import Entity.panier_holder;
import Entity.produit;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author pc
 */
public class PanierService {
    
    Connection cnx = MyConnection.getInstance().getConnection(); 
    Statement st;
    PreparedStatement ste ;
    
    public List<Element_panier> initPanier() {
        
         
       String req="select * from panier_temp, produit where panier_temp.user_id=1 and produit.ref_produit=panier_temp.ref_produit";
        List<Element_panier> ListF =new ArrayList<>();
        
        try {
            Statement st = cnx.createStatement();
        
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Element_panier a = new Element_panier();
                produit p=new produit ();
                p.setRef_produit(rs.getInt("ref_produit"));
                p.setUser_id(rs.getInt("user_id"));
                p.setNom_produit(rs.getString("nom_produit"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix_produit(rs.getFloat("prix_produit"));
                p.setQuantite_produit(rs.getInt("quantite_produit"));
                p.setDate_expiration(rs.getString("date_expiration"));
                p.setImage(rs.getString("image"));
             
                a.setProd(p);
                a.setQuantite_produit(rs.getInt("quantite_produit"));
                
                
                                                                              
                                        ListF.add(a);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);   
        }
        
        return ListF;
    
    }
    
           
        public void createPanier(int user_id) { 
        try {
            
            float prixtot=0;
            List<Element_panier> elementPaniers=panier_holder.getInstance().getEP();
            
            for ( int i=0;i<elementPaniers.size();i++)
            {
                prixtot+=elementPaniers.get(i).getProd().getPrix_produit()*elementPaniers.get(i).getQuantite_produit();
            }
            
            String req = "INSERT INTO commande (user_id,PrixTot,Date) VALUES (?,?,?)";
            
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, user_id);
            pst.setFloat(2, prixtot);
            pst.setDate(3, new Date(System.currentTimeMillis()));
            
            if(pst.executeUpdate() >0) 
            {
                System.out.println("added with success");
                ajouterPanierElems();

            }
            else
                System.out.println("failed");
            
        } catch (SQLException ex) {
            System.out.println("exception =="+ex.getMessage());
        }

    }
        
        public void ajouterPanierElems()
        {
            String req="SELECT Max(commande_id) FROM commande";
  
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
              int id_cmd=0;
               List<Element_panier> elementPaniers=panier_holder.getInstance().getEP();
            if(rs.next())
              id_cmd=rs.getInt(1);
            
              
            
            for ( int i=0;i<elementPaniers.size();i++)
            {
           
            String insReq="INSERT INTO panier ( `commande_id`,`ref_produit`,`quantite_produit`) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(insReq);
            pst.setInt(1, id_cmd);
            pst.setInt(2,elementPaniers.get(i).getProd().getRef_produit());
            pst.setInt(3,elementPaniers.get(i).getQuantite_produit());
            
              if(pst.executeUpdate() >0) 
            {
                System.out.println("added with success");

            }
             }
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        updateOeuvres();
                
                
        }
        public void updatePanierTemp(int user_id,int ref_produit,int quantite_produit)
        {   
            try{
                
            {
                String insreq="UPDATE panier_temp SET quantite_produit=quantite_produit+? WHERE ref_produit=? AND user_id=?";
                PreparedStatement pst = cnx.prepareStatement(insreq);
//                pst.setInt(1, elementPaniers.get(i).getQuantite());
                pst.setInt(1, quantite_produit);
                pst.setInt(2, ref_produit);
                pst.setInt(3, user_id);
                

         
                if(pst.executeUpdate() >0) 
                {
                    System.out.println("Panier Temp updated with success");

                }
            }   
                
            }catch (SQLException ex){
                Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        
        public void createPanierTemp(int user_id, int ref_produit, int quantite_produit) { 
        
            try { 
              
                
            String insReq="INSERT INTO `panier_temp`( `user_id`, `ref_produit`, `quantite_produit`) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(insReq);
            
            
            pst.setInt(1, user_id);
            pst.setInt(2, ref_produit );
            pst.setInt(3, quantite_produit);
            
            
              if(pst.executeUpdate() >0) 
            {
                System.out.println("added To panier temp");
            }
              
             
            
            
        }catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
        public void updateOeuvres()
        {
             try {
         
               List<Element_panier> elementPaniers=panier_holder.getInstance().getEP();
            
            for ( int i=0;i<elementPaniers.size();i++)
            {
           
                String insReq="UPDATE produit SET quantite_produit=quantite_produit-? WHERE ref_produit=?";

                PreparedStatement pst = cnx.prepareStatement(insReq);
                pst.setInt(1, elementPaniers.get(i).getQuantite_produit());
                pst.setInt(2, elementPaniers.get(i).getProd().getRef_produit());

                  if(pst.executeUpdate() >0) 
                {
                    System.out.println("updated with success");

                }
             }
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        public void deletepaniert(int user_id){
         
                 String req="DELETE FROM panier_temp WHERE user_id=?";
                 try{
                     PreparedStatement pst = cnx.prepareStatement(req);
                     
                     pst.setInt(1, user_id);
                     pst.executeUpdate();
                       
                     
                     
                             
                 } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        }
        
        public void deletepaniertElem(int user_id, int ref_produit){
         
                 String req="DELETE FROM panier_temp WHERE user_id=? AND ref_produit=?";
                 try{
                     PreparedStatement pst = cnx.prepareStatement(req);
                     
                     pst.setInt(1, user_id);
                     pst.setInt(2, ref_produit);
                     pst.executeUpdate();
                       
                     
                     
                             
                 } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        }
    
    
}
