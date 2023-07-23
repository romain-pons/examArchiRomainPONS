package com.example.examarchiromainpons.repository;

import com.example.examarchiromainpons.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    /**
     * Récupère le nombre de courses réalisées par un client en fonction de son ID
     * @param id ID du Client
     * @return Nombre de courses
     */
    @Query("SELECT COUNT(c) FROM CourseEntity c WHERE c.client.ID = :id AND c.client.type = 'client'")
    long findNumberOfCoursesOfClientByID(int id);

    /**
     * Récupère la distance totale parcourue par un client en fonction de son ID
     * @param id ID du Client
     * @return Distance totale
     */
    @Query("SELECT SUM(c.distance) FROM CourseEntity c WHERE c.client.ID = :id AND c.client.type = 'client'")
    double getTotalDistanceOfClientByID(int id);

    // Additionne les distances parcourues des chauffeurs,
    // en regroupant leurs infos + en limitant la requête à 3 réponses
    @Query("SELECT u, SUM(c.distance) as totalDistance " +
            "FROM UtilisateurEntity u " +
            "JOIN CourseEntity c ON u.ID = c.chauffeur.ID " +
            "WHERE u.type = 'chauffeur_voiture' OR u.type = 'motard' " +
            "GROUP BY u.ID, u.nom, u.prenom, u.dateNaissance, u.dateInscription, u.type " +
            "ORDER BY totalDistance DESC LIMIT 3")
    List<Object[]> findTop3ChauffeursByTotalDistance();
}
