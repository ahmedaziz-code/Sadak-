// بسم الله الرحمن الرحيم 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Connection.MyConnection;
import Entity.Event;
import java.io.FileInputStream;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
/**
 *
 * @author alaak
 */


public class EventService {
    
    public Connection cnx;
    public PreparedStatement ste;
    
    public EventService(){
        cnx = MyConnection.getInstance().getConnection();
    }
    
   
    public void ajouterEvent(Event e) {
        try {
            //String sql = "insert into News(event_id,event_name,event_content,event_date)values(?,?,?,?)";
            String sql = "insert into News(event_id,event_name,event_content,event_date,event_dateF,event_image)values(?,?,?,?,?,?)";
            ste = cnx.prepareStatement(sql);
            ste.setInt(1,e.getEvent_id());
            ste.setString(2,e.getEvent_name());
            ste.setString(3,e.getEvent_content());
            ste.setString(4,e.getEvent_date());
            ste.setString(5,e.getEvent_dateF());
            //ste.setString(5,e.getEvent_organiser());
            ste.setString(6,e.getEvent_image());
//            AddEventController test=new AddEventController();
//            File file=test.getfile();
//            
//            
//            ste.setBinaryStream(5, e.getEvent_fis(),file.length());
//            
            
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void ajouterEventt(Event e) {
        try {
            //String sql = "insert into News(event_id,event_name,event_content,event_date)values(?,?,?,?)";
            String sql = "insert into News(event_id,event_name,event_content,event_date,event_dateF,event_organiser,event_image)values(?,?,?,?,?,?,?)";
            ste = cnx.prepareStatement(sql);
            ste.setInt(1,e.getEvent_id());
            ste.setString(2,e.getEvent_name());
            ste.setString(3,e.getEvent_content());
            ste.setString(4,e.getEvent_date());
            ste.setString(5,e.getEvent_dateF());
            ste.setString(6,e.getEvent_organiser());
            ste.setString(7,e.getEvent_image());
//            AddEventController test=new AddEventController();
//            File file=test.getfile();
//            
//            
//            ste.setBinaryStream(5, e.getEvent_fis(),file.length());
//            
            
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

     public void modifierEvent(Event e) {
        try {
            
    
            
           String sql="UPDATE `news` SET "
                    + "`event_name`=?,"
                    + "`event_date`=?,"
                    + "`event_dateF`=?,"
                    + "`event_image`=?,"
                    + "`event_organiser`=?,"
                    + "`event_content`= ? WHERE event_id = '"+e.getEvent_id()+"'";
             ste = cnx.prepareStatement(sql);
                   ste.setString(1,e.getEvent_name());
                   ste.setString(2,e.getEvent_date());
                   ste.setString(3,e.getEvent_dateF());
                   ste.setString(4,e.getEvent_image());
            ste.setString(6,e.getEvent_content());
            ste.setString(5,e.getEvent_organiser());
           
            
         
           // AddEventController test=new AddEventController();
           // File file=test.getfile();
            //FileInputStream fis=new FileInputStream(file);
            //ste.setBinaryStream(5, (InputStream)fis,(int)file.length());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimerEvent(int event_id){
        try {
            ste = cnx.prepareStatement("delete from News where event_id=?");
            ste.setInt(1, event_id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void rechercherEvent(int event_id){
        try {
            List<Event>events = new ArrayList<>();
            ste = cnx.prepareStatement("select * from News where event_id='"+event_id+"'");
            ResultSet rs = ste.executeQuery();
            Event e = new Event();
//            ste.setInt(1, id_f);
//            ste.executeUpdate();
            while(rs.next()){
                e.setEvent_id(event_id);
                e.setEvent_content(rs.getString("event_content"));
                e.setEvent_organiser(rs.getString("event_organiser"));
                e.setEvent_date(rs.getString("event_date"));
               
                events.add(e);
            }
            System.out.println(events);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
