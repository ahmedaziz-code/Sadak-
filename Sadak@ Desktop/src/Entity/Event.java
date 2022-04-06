// بسم الله الرحمن الرحيم 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.FileInputStream;

/**
 *
 * @author alaak
 */
public class Event {
    int event_id;
    boolean event_participation;
    String event_content,event_organiser,event_date,event_dateF,event_image,event_name;
    //private FileInputStream fis;
    
    public Event(){
}
    public Event(String event_organiser,int event_id,String event_name,String event_date,String event_dateF,String event_content,String event_image){
      
        this.event_content=event_content;
        this.event_date=event_date;
        this.event_dateF=event_dateF;
      this.event_name=event_name;
      this.event_image=event_image;
        this.event_organiser=event_organiser;
        this.event_id=event_id;
    }
  
    
       public Event(String event_organiser,int event_id,String event_name,String event_date,String event_content,String event_image){
      
        this.event_content=event_content;
        this.event_date=event_date;
      this.event_name=event_name;
      this.event_image=event_image;
        this.event_organiser=event_organiser;
        this.event_id=event_id;
    }
public Event(String event_organiser,String event_name,String event_date,String event_dateF,String event_content,String event_image){
        
        this.event_content=event_content;
        this.event_date=event_date;
        this.event_dateF=event_dateF;
        this.event_organiser=event_organiser;
       this.event_image=event_image;
       this.event_name=event_name;
}
   
public Event(int event_id,String event_name,String event_content,String event_organiser,String event_date,String event_image){
        this.event_id=event_id;
        this.event_content=event_content;
        this.event_date=event_date;
        this.event_organiser=event_organiser;
       this.event_image=event_image;
       this.event_name=event_name;
}
public Event(int event_id,String event_name,String event_content,String event_organiser,String event_date,String event_dateF,String event_image){
        this.event_id=event_id;
        this.event_content=event_content;
        this.event_date=event_date;
        this.event_dateF=event_dateF;
        this.event_organiser=event_organiser;
       this.event_image=event_image;
       this.event_name=event_name;
}
 
    

       
   public int getEvent_id() {
        return event_id;
    } 
    
//    public FileInputStream getEvent_fis() {
//        return fis;
//    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_content() {
        return event_content;
    }

    public void setEvent_content(String event_content) {
        this.event_content = event_content;
    }

    public String getEvent_organiser() {
        return event_organiser;
    }

    public void setEvent_organiser(String event_organiser) {
        this.event_organiser = event_organiser;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }
     public String getEvent_dateF() {
        return event_dateF;
    }

    public void setEvent_dateF(String event_dateF) {
        this.event_dateF = event_dateF;
    }
    
     public String getEvent_image() {
        return event_image;
    }

    public void setEvent_image(String event_image) {
        this.event_image = event_image;
    }
     public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }
    
    
    @Override
    public String toString() {
        return "Events{" + "event_id=" + event_id +",event_date"+event_date+ ", event_content=" + event_content + ", event_organiser=" + event_organiser +'}';
    }

    
}