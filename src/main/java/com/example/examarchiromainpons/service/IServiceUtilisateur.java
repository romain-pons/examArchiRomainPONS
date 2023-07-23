package com.example.examarchiromainpons.service;

import com.example.examarchiromainpons.dto.UtilisateurDto;
import com.example.examarchiromainpons.entity.UtilisateurEntity;

public interface IServiceUtilisateur {
    Boolean exist(int ID);

    Boolean AddUtilisateur(UtilisateurDto dto);

    UtilisateurDto toDto(UtilisateurEntity entity);

    UtilisateurEntity toEntity(UtilisateurDto dto);
}
