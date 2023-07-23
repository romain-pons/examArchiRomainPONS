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
}
