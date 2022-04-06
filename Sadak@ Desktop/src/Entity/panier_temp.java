/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author pc
 */
public class panier_temp {
    private int id , 	user_id , ref_produit,quantite_produit	;

    public panier_temp() {
    }

    public panier_temp(int id, int user_id, int ref_produit, int quantite_produit) {
        this.id = id;
        this.user_id = user_id;
        this.ref_produit = ref_produit;
        this.quantite_produit = quantite_produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRef_produit() {
        return ref_produit;
    }

    public void setRef_produit(int ref_produit) {
        this.ref_produit = ref_produit;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
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
        final panier_temp other = (panier_temp) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
