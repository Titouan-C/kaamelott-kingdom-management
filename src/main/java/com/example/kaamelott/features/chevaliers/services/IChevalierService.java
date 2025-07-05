package com.example.kaamelott.features.chevaliers.services;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.features.chevaliers.dtos.in.InChevalierDto;
import com.example.kaamelott.features.chevaliers.dtos.out.OutChevalierDto;
import com.example.kaamelott.features.chevaliers.dtos.out.OutChevalierQueteDto;
import com.example.kaamelott.features.chevaliers.entities.ChevalierEntity;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing Chevaliers in the Kaamelott application.
 */
public interface IChevalierService {

    ChevalierEntity getChevalierById(UUID chevalierId);

    OutPaginatedDataDto<List<OutChevalierDto>> getAll(Integer cursor, Integer pageSize);

    OutChevalierDto createChevalier(InChevalierDto inChevalierDto);

    OutPaginatedDataDto<List<OutChevalierQueteDto>> getQuetesEnCours(UUID chevalierId, Integer cursor, Integer pageSize);

    void retirerQuete(UUID chevalierId, UUID queteId);

    // UTILS

    List<String> getAllTitres();

    boolean isTitreExists(String titre);

    boolean isChevalierExists(UUID chevalierId);
}
