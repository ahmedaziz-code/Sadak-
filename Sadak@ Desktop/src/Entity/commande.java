/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author pc
 */
public class commande {
    private int commande_id ,user_id  ;
    private Date date ;
    private float PrixTot ;

    public commande() {
    }

    public commande(int commande_id, int user_id, Date date, float PrixTot) {
        this.commande_id = commande_id;
        this.user_id = user_id;
        this.date = date;
        this.PrixTot = PrixTot;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final commande other = (commande) obj;
        if (this.commande_id != other.commande_id) {
            return false;
        }
        return true;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrixTot() {
        return PrixTot;
    }

    public void setPrixTot(float PrixTot) {
        this.PrixTot = PrixTot;
    }
    
    
    
    
    
    
    
}
