package com.example.appsecondfirebase;

import android.net.Uri;

public class Etudiant {
    private String nom,prenom , tel, photo;
    public Etudiant(){}

    public Etudiant(String nom, String prenom, String tel, String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.photo = photo;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
