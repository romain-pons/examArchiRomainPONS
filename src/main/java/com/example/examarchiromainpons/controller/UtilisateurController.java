package com.example.examarchiromainpons.controller;

import com.example.examarchiromainpons.dto.UtilisateurDto;
import com.example.examarchiromainpons.entity.UtilisateurEntity;
import com.example.examarchiromainpons.entity.enums.UtilisateurType;
import com.example.examarchiromainpons.service.ServiceUtilisateur;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("utilisateurs")
public class UtilisateurController {

    @Autowired
    private ServiceUtilisateur serviceClient;

    /**
     * Ajoute un utilisateur dans la DB
     * @param dto UserDto
     * @return
     */
    @PostMapping("add")
    public ResponseEntity<Boolean> ajouterUtilisateur(@RequestBody UtilisateurDto dto){

        // checker si l'utilisateur n'existe pas deja
        if (serviceClient.exist(dto.getID())){
            return new ResponseEntity("le client existe deja", HttpStatus.BAD_REQUEST);
        }

        // Vérifie le format de la date
        try {
            LocalDateTime.parse(dto.getDateNaissance().toString());
            LocalDateTime.parse(dto.getDateInscription().toString());
        }catch (Exception e){
            return new ResponseEntity("la date n'est pas bonne", HttpStatus.BAD_REQUEST);
        }

        // Checker si le type renseigné est bien existant dans la class enum
        if(dto.getType() != UtilisateurType.client &&
                dto.getType() != UtilisateurType.motard &&
                dto.getType() != UtilisateurType.chauffeur_voiture){
            return new ResponseEntity("Vous ne pouvez pas ajouter un type autre que client, motard, ou chauffeur_voiture !", HttpStatus.BAD_REQUEST);
        }

        if (!serviceClient.AddUtilisateur(dto)){
            return new ResponseEntity("une erreur est survenue", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    /**
     * Permet de récupérer la liste des utilisateurs existants dans la base de données
     * @return Array d'objet contenant la liste des utilisateurs enregistrés dans la DB
     */
    @GetMapping("/listUsers")
    public List<UtilisateurEntity> getAllUsers() {
        return serviceClient.getAllUsers();
    }

    /**
     * Récupère les données d'un utilisateur en fonction de son ID
     * @param id ID de l'utilisateur (en DB)
     * @return Objet contenant les données de l'utilisateur en question
     */
    @GetMapping("/findUser/{id}")
    public Optional<UtilisateurEntity> getUserById(@PathVariable int id) {
        return serviceClient.findById(id);
    }

    /**
     * Supprime un utilisateur en fonction de son ID
     * @param id ID de l'utilisateur
     */
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable int id){
        serviceClient.deleteUserFromID(id);
    }



    @GetMapping("/countByType")
    public Map<String, Long> countUsersByType() {
        List<UtilisateurEntity> utilisateurs = serviceClient.getAllUsers();
        // Check si des utilisateurs n'existent pas en DB
        if(serviceClient.getAllUsers() == null ) {
            return null;
        }
        Map<String, Long> counts = new HashMap<>();
        // Pour chaque utilisateurs, on additionne la quantité de types enregistrés en DB
        for (UtilisateurEntity utilisateur : utilisateurs) {
            String type = utilisateur.getType().toString();
            Long count = counts.getOrDefault(type, 0L);
            counts.put(type, count + 1);
        }
        // Check si counts est null
        if(counts == null) {
            return null;
        }
        return counts;
    }
}
