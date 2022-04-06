/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author pc
 */
public class Element_panier {
    private produit prod ;
    private int quantite_produit ;

    public Element_panier() {
    }

    public Element_panier(produit prod, int quantite_produit) {
        this.prod = prod;
        this.quantite_produit = quantite_produit;
    }

    public produit getProd() {
        return prod;
    }

    public void setProd(produit prod) {
        this.prod = prod;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    @Override
    public String toString() {
        return "panier_holder{" + "prod=" + prod + ", quantite_produit=" + quantite_produit + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        final Element_panier other = (Element_panier) obj;
        if (!Objects.equals(this.prod.getRef_produit(), other.prod.getRef_produit())) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
}
