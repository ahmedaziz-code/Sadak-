package Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pc
 */
public class produit {

   private int ref_produit , quantite_produit , user_id;
   private String nom_produit ,categorie ,date_expiration,image ;
   private float prix_produit ;

    public produit() {
    }

    public produit(int ref_produit, int quantite_produit, int user_id, String nom_produit, String categorie, String date_expiration, String image, float prix_produit) {
        this.ref_produit = ref_produit;
        this.quantite_produit = quantite_produit;
        this.user_id = user_id;
        this.nom_produit = nom_produit;
        this.categorie = categorie;
        this.date_expiration = date_expiration;
        this.image = image;
        this.prix_produit = prix_produit;
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

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(String date_expiration) {
        this.date_expiration = date_expiration;
    }

    public float getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        final produit other = (produit) obj;
        if (this.ref_produit != other.ref_produit) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "produit{" + "ref_produit=" + ref_produit + ", quantite_produit=" + quantite_produit + ", user_id=" + user_id + ", nom_produit=" + nom_produit + ", categorie=" + categorie + ", date_expiration=" + date_expiration + ", image=" + image + ", prix_produit=" + prix_produit + '}';
    }
    
    
}
