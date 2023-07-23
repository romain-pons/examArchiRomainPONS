package com.example.examarchiromainpons.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private UtilisateurEntity client;

    @ManyToOne
    @JoinColumn(name = "id_chauffeur", referencedColumnName = "id")
    private UtilisateurEntity chauffeur;

    @Column(name = "date_course")
    private LocalDate dateCourse;

    @Column(name = "temps_trajet")
    private int tempsTrajet;

    @Column(name = "distance")
    private float distance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UtilisateurEntity getClient() {
        return client;
    }

    public void setClient(UtilisateurEntity client) {
        this.client = client;
    }

    public UtilisateurEntity getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(UtilisateurEntity chauffeur) {
        this.chauffeur = chauffeur;
    }

    public LocalDate getDateCourse() {
        return dateCourse;
    }

    public void setDateCourse(LocalDate dateCourse) {
        this.dateCourse = dateCourse;
    }

    public int getTempsTrajet() {
        return tempsTrajet;
    }

    public void setTempsTrajet(int tempsTrajet) {
        this.tempsTrajet = tempsTrajet;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


}
