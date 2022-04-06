/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;
import Services.PanierService;

/**
 *
 * @author pc
 */
public class panier_holder {
    
    private List<Element_panier> EP ;
    private static panier_holder instance ;
    
    
    public static panier_holder getInstance() {
        if (instance==null )
            instance = new panier_holder ();
        return instance ;
                
        
    }
    
    public panier_holder() {
        PanierService ps = new PanierService ();
        this.EP= ps.initPanier(); 
    }
    public List<Element_panier> getEP () {
        return EP ;
   
                
    }
    
    public void setEP (List<Element_panier> EP){
        this.EP = EP ;
        
    }
    public void removeEP (Element_panier EP ) {
        this.EP.remove(EP);
        
    }
}

