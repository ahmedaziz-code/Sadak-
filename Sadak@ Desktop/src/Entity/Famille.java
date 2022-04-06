/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javafx.scene.control.Button;

/**
 *
 * @author ahmed
 */
public class Famille {
    
    int id_f,tele_p,tele_m,nbr_enf,nbr_enf_malade;
    String pays_f,etat_f,ville_f,nom_p,prenom_p,d_naissace_p
            ,s_revenue_p,nom_m,prenom_m,d_naissance_m
            ,s_revenue_m,besoin_f,verif_f,remarque,date_v;
    float r_brut_f;
    

    public Famille() {
    }

    public Famille(int id_f, String nom_p, String verif_f, String remarque, String date_v) {
        this.id_f = id_f;
        this.nom_p = nom_p;
        this.verif_f = verif_f;
        this.remarque = remarque;
        this.date_v = date_v;
    }
    
    

    public Famille(int id_f, String nom_p, String verif_f) {
        this.nom_p = nom_p;
        this.verif_f = verif_f;
        this.id_f = id_f;
        
    }

    

    public Famille(int id_f, int tele_p, int tele_m, int nbr_enf,
            int nbr_enf_malade, String pays_f, String etat_f,
            String ville_f, String nom_p, String prenom_p,
            String d_naissace_p, String s_revenue_p, String nom_m,
            String prenom_m, String d_naissance_m, String s_revenue_m,
            String besoin_f, String verif_f, float r_brut_f) {
        this.id_f = id_f;
        
        this.tele_p = tele_p;
        this.tele_m = tele_m;
        this.nbr_enf = nbr_enf;
        this.nbr_enf_malade = nbr_enf_malade;
        this.pays_f = pays_f;
        this.etat_f = etat_f;
        this.ville_f = ville_f;
        this.nom_p = nom_p;
        this.prenom_p = prenom_p;
        this.d_naissace_p = d_naissace_p;
        this.s_revenue_p = s_revenue_p;
        this.nom_m = nom_m;
        this.prenom_m = prenom_m;
        this.d_naissance_m = d_naissance_m;
        this.s_revenue_m = s_revenue_m;
        this.besoin_f = besoin_f;
        this.verif_f = verif_f;
        this.r_brut_f = r_brut_f;
        
    }

    public Famille(int id_f, int tele_p, int tele_m, int nbr_enf
            , int nbr_enf_malade, String pays_f, String etat_f
            , String ville_f, String nom_p, String prenom_p
            , String d_naissace_p, String s_revenue_p, String nom_m
            , String prenom_m, String d_naissance_m, String s_revenue_m
            , String besoin_f, String verif_f, String remarque
            , String date_v, float r_brut_f) {
        this.id_f = id_f;
        this.tele_p = tele_p;
        this.tele_m = tele_m;
        this.nbr_enf = nbr_enf;
        this.nbr_enf_malade = nbr_enf_malade;
        this.pays_f = pays_f;
        this.etat_f = etat_f;
        this.ville_f = ville_f;
        this.nom_p = nom_p;
        this.prenom_p = prenom_p;
        this.d_naissace_p = d_naissace_p;
        this.s_revenue_p = s_revenue_p;
        this.nom_m = nom_m;
        this.prenom_m = prenom_m;
        this.d_naissance_m = d_naissance_m;
        this.s_revenue_m = s_revenue_m;
        this.besoin_f = besoin_f;
        this.verif_f = verif_f;
        this.remarque = remarque;
        this.date_v = date_v;
        this.r_brut_f = r_brut_f;
    }
    
    

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getDate_v() {
        return date_v;
    }

    public void setDate_v(String date_v) {
        this.date_v = date_v;
    }

    
    
    

    

    public int getId_f() {
        return id_f;
    }

    

    public int getTele_p() {
        return tele_p;
    }

    public int getTele_m() {
        return tele_m;
    }

    public int getNbr_enf() {
        return nbr_enf;
    }

    public int getNbr_enf_malade() {
        return nbr_enf_malade;
    }

    

    public String getVille_f() {
        return ville_f;
    }

    public String getNom_p() {
        return nom_p;
    }

    public String getPrenom_p() {
        return prenom_p;
    }

    public String getD_naissace_p() {
        return d_naissace_p;
    }

    public String getS_revenue_p() {
        return s_revenue_p;
    }

    public String getNom_m() {
        return nom_m;
    }

    public String getPrenom_m() {
        return prenom_m;
    }

    public String getD_naissance_m() {
        return d_naissance_m;
    }

    public String getS_revenue_m() {
        return s_revenue_m;
    }

    public String getBesoin_f() {
        return besoin_f;
    }

    public float getR_brut_f() {
        return r_brut_f;
    }

    public void setId_f(int id_f) {
        this.id_f = id_f;
    }

    

    public void setTele_p(int tele_p) {
        this.tele_p = tele_p;
    }

    public void setTele_m(int tele_m) {
        this.tele_m = tele_m;
    }

    public void setNbr_enf(int nbr_enf) {
        this.nbr_enf = nbr_enf;
    }

    public void setNbr_enf_malade(int nbr_enf_malade) {
        this.nbr_enf_malade = nbr_enf_malade;
    }

    

    public void setVille_f(String ville_f) {
        this.ville_f = ville_f;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public void setPrenom_p(String prenom_p) {
        this.prenom_p = prenom_p;
    }

    public void setD_naissace_p(String d_naissace_p) {
        this.d_naissace_p = d_naissace_p;
    }

    public void setS_revenue_p(String s_revenue_p) {
        this.s_revenue_p = s_revenue_p;
    }

    public void setNom_m(String nom_m) {
        this.nom_m = nom_m;
    }

    public void setPrenom_m(String prenom_m) {
        this.prenom_m = prenom_m;
    }

    public void setD_naissance_m(String d_naissance_m) {
        this.d_naissance_m = d_naissance_m;
    }

    public void setS_revenue_m(String s_revenue_m) {
        this.s_revenue_m = s_revenue_m;
    }

    public void setBesoin_f(String besoin_f) {
        this.besoin_f = besoin_f;
    }

    public void setR_brut_f(float r_brut_f) {
        this.r_brut_f = r_brut_f;
    }

    public String getVerif_f() {
        return verif_f;
    }

    public void setVerif_f(String verif_f) {
        this.verif_f = verif_f;
    }

    public String getPays_f() {
        return pays_f;
    }

    public void setPays_f(String pays_f) {
        this.pays_f = pays_f;
    }

    public String getEtat_f() {
        return etat_f;
    }

    public void setEtat_f(String etat_f) {
        this.etat_f = etat_f;
    }

    @Override
    public String toString() {
        return "Famille{" + "id_f=" + id_f + ", tele_p=" 
                + tele_p + ", tele_m=" + tele_m + ", nbr_enf=" 
                + nbr_enf + ", nbr_enf_malade=" + nbr_enf_malade + ", pays_f=" 
                + pays_f + ", etat_f=" + etat_f + ", ville_f=" + ville_f + ", nom_p=" 
                + nom_p + ", prenom_p=" + prenom_p + ", d_naissace_p=" 
                + d_naissace_p + ", s_revenue_p=" + s_revenue_p + ", nom_m=" 
                + nom_m + ", prenom_m=" + prenom_m + ", d_naissance_m=" 
                + d_naissance_m + ", s_revenue_m=" + s_revenue_m + ", besoin_f=" 
                + besoin_f + ", verif_f=" + verif_f + ", r_brut_f=" + r_brut_f + ", update=" +'}';
    }
    
    
    
    
    
}
