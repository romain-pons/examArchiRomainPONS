package com.example.examarchiromainpons.service;

import com.example.examarchiromainpons.dto.UtilisateurDto;
import com.example.examarchiromainpons.entity.UtilisateurEntity;
import com.example.examarchiromainpons.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUtilisateur implements IServiceUtilisateur {
    /**
     * Repository de l'UserEntity
     */
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    /**
     * Vérifie que l'utilisateur existe en DB
     * @param ID ID de l'user
     * @return Valeur booléenne si l'user existe ou non (true ou false)
     */
    @Override
    public Boolean exist(int ID) {
        return utilisateurRepository.existsById(ID);
    }

    /**
     * Récupère les données d'un user par son ID
     * @param ID ID de l'user
     * @return Objet contenant les infos de l'user
     */
    public Optional<UtilisateurEntity> findById(int ID) {
        return utilisateurRepository.findById(ID);
    }

    /**
     * Supprime un utilisateur en fonction d'un ID
     * @param ID ID de l'user
     */
    public void deleteUserFromID(int ID) {
        utilisateurRepository.deleteById(ID);
    }

    /**
     * Récupère tous les utilisateurs présents en DB
     * @return Array d'objets contenant les utilisateurs
     */
    public List<UtilisateurEntity> getAllUsers(){
        return utilisateurRepository.findAll();
    }

    /**
     * Ajoute un utilisateur en DB depuis le DTO (en passant par une conversion du DTO vers Entity)
     * @param dto UserDto
     * @return Boolean
     */
    @Override
    public Boolean AddUtilisateur(UtilisateurDto dto) {
        UtilisateurEntity entity = toEntity(dto);
        try {
            utilisateurRepository.saveAndFlush(entity);
            return  true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UtilisateurDto toDto(UtilisateurEntity entity) {
        return null;
    }

    /**
     * Convertir un DTO en une Entity
     * @param dto UserDto
     * @return
     */
    @Override
    public UtilisateurEntity toEntity(UtilisateurDto dto) {
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setID(dto.getID());
        utilisateurEntity.setNom(dto.getNom());
        utilisateurEntity.setPrenom(dto.getPrenom());
        utilisateurEntity.setType(dto.getType());
        utilisateurEntity.setDateInscription(dto.getDateInscription());
        utilisateurEntity.setDateNaissance(dto.getDateNaissance());
        return utilisateurEntity;
    }
}
