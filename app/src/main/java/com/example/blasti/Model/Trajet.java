package com.example.blasti.Model;

public class Trajet {
    private String depart;
    private String arrivee;
    private String date;
    private String heure;
    private String prix;
    private  String nomChauffeur;

    public Trajet (String depart, String arrivee, String date, String heure, String prix, String nomChauffeur) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.date = date;
        this.heure = heure;
        this.prix = prix;
        this.nomChauffeur = nomChauffeur;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getNomChauffeur() {
        return nomChauffeur;
    }

    public void setNomChauffeur(String nomChauffeur) {
        this.nomChauffeur = nomChauffeur;
    }
}
