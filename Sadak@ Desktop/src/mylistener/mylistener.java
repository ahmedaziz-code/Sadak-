/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylistener;

/**
 *
 * @author pc
 */

import Entity.Element_panier ;
import Entity.produit ;
import javafx.event.ActionEvent;


import javafx.scene.input.MouseEvent;

public interface mylistener {
    
    public void onClickListener(MouseEvent event, produit oeuvre) ;
    public void onpressed(ActionEvent event , produit oeuvre ) ;
    public void onClickListener (Element_panier EP ) ;

    
    
    
}
