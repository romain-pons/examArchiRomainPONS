package com.example.examarchiromainpons.dto;

import com.example.examarchiromainpons.entity.enums.UtilisateurType;

import java.time.LocalDate;

public class UtilisateurDto {

    private int ID;
    private String nom, prenom;

    private LocalDate dateNaissance, dateInscription;

    private UtilisateurType type;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public UtilisateurType getType() {
        return type;
    }

    public void setType(UtilisateurType type) {
        this.type = type;
    }
}
