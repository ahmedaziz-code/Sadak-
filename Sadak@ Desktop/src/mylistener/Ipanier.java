/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylistener;

import java.util.List;

/**
 *
 * @author pc
 */
public interface Ipanier <T> {
    public void ajouter (T p ) ;
    public void Updatecmd (T p) ;
    public void Delcmd (T p);
    public List <T> afficherTP();
    public List <T> displayALL() ;
    public void getALl () ;
    public T afficherP (int id ) ;
    
    
    
}
