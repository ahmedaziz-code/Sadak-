/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Serviceinfo
 */
public class User {
    
    String username,nom_u,prenom_u,email_u,mdp_u,type_u;

    public User() {
    }

    public User(String username, String nom_u, String prenom_u, String email_u, String mdp_u, String type_u) {
        this.username = username;
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.email_u = email_u;
        this.mdp_u = mdp_u;
        this.type_u = type_u;
    }

    public User(String email_u) {
        this.email_u = email_u;
    }

    

    public String getUsername() {
        return username;
    }

    public String getNom_u() {
        return nom_u;
    }

    public String getPrenom_u() {
        return prenom_u;
    }

    public String getEmail_u() {
        return email_u;
    }

    public String getMdp_u() {
        return mdp_u;
    }

    public String getType_u() {
        return type_u;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNom_u(String nom_u) {
        this.nom_u = nom_u;
    }

    public void setPrenom_u(String prenom_u) {
        this.prenom_u = prenom_u;
    }

    public void setEmail_u(String email_u) {
        this.email_u = email_u;
    }

    public void setMdp_u(String mdp_u) {
        this.mdp_u = mdp_u;
    }

    public void setType_u(String type_u) {
        this.type_u = type_u;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", nom_u=" + nom_u + ", prenom_u=" + prenom_u + ", email_u=" + email_u + ", mdp_u=" + mdp_u + ", type_u=" + type_u + '}';
    }
    
    
}
