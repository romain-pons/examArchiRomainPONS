package com.example.examarchiromainpons.controller;

import com.example.examarchiromainpons.dto.ChauffeurSummaryDto;
import com.example.examarchiromainpons.dto.CourseDto;
import com.example.examarchiromainpons.entity.UtilisateurEntity;
import com.example.examarchiromainpons.repository.CourseRepository;
import com.example.examarchiromainpons.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository courseRepository;
    private final UtilisateurRepository utilisateurRepository;


    @Autowired
    public CourseController(CourseRepository courseRepository, UtilisateurRepository utilisateurRepository) {
        this.courseRepository = courseRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Récupère le nombre de courses et la distance, ainsi que le nom du Client
     * En fonction de son ID
     * @param clientId ID du Client
     * @return CourseDto
     */
    @GetMapping("/client/{clientId}/summary")
    @ResponseBody
    public CourseDto getClientCourseSummary(@PathVariable int clientId) {
        long nbCourse = courseRepository.findNumberOfCoursesOfClientByID(clientId);
        double totalDistance = courseRepository.getTotalDistanceOfClientByID(clientId);

        // Check si le client ID n'est pas valide
        if(!(utilisateurRepository.existsById(clientId)) || clientId <= 0) {
            return null;
        }

        // Check pour vérifier que les valeurs ne sont pas égales à 0
        if(nbCourse == 0 || totalDistance == 0){
            return null;
        }

        UtilisateurEntity user = utilisateurRepository.findById(clientId).orElse(null);

        // Check si l'user n'existe pas
        if(user == null) {
            return null;
        }

        CourseDto summaryDTO = new CourseDto();
        summaryDTO.setClientName(user.getNom() + " " + user.getPrenom()); // Vous pouvez récupérer le nom du client à partir de la base de données si nécessaire.
        summaryDTO.setNbCourses(nbCourse);
        summaryDTO.setDistance(totalDistance);

        return summaryDTO;
    }

    /**
     * Récupère les trois meilleurs chauffeurs en fonction de la distance parcourue
     * @return
     */
    @GetMapping("/top3")
    public List<ChauffeurSummaryDto> getTop3ChauffeursByTotalDistance() {
        List<Object[]> top3Chauffeurs = courseRepository.findTop3ChauffeursByTotalDistance();
        List<ChauffeurSummaryDto> chauffeurSummaries = new ArrayList<>();

        // Check s'il ne trouve pas le TOP3 des chauffeurs
        if(top3Chauffeurs == null) {
            return null;
        }

        // On établit un objet pour chaque chauffeur dans le TOP3
        for (Object[] obj : top3Chauffeurs) {
            UtilisateurEntity chauffeur = (UtilisateurEntity) obj[0];
            double totalDistance = (double) obj[1];

            // Check si le chauffeur est null ou si la totalDistance est égale à 0
            if(chauffeur == null || totalDistance == 0){
                return null;
            }

            ChauffeurSummaryDto chauffeurSummary = new ChauffeurSummaryDto();
            chauffeurSummary.setNom(chauffeur.getNom());
            chauffeurSummary.setPrenom(chauffeur.getPrenom());
            chauffeurSummary.setDistance(totalDistance);
            chauffeurSummaries.add(chauffeurSummary);
        }

        return chauffeurSummaries;
    }

}
