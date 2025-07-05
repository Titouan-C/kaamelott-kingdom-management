package com.example.kaamelott.features.chevaliers.services;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.features.chevaliers.dtos.in.InChevalierDto;
import com.example.kaamelott.features.chevaliers.dtos.out.OutChevalierDto;

import java.util.List;

/**
 * Service interface for managing Chevaliers in the Kaamelott application.
 */
public interface IChevalierService {
    OutPaginatedDataDto<List<OutChevalierDto>> getAll(Integer cursor, Integer pageSize);

    OutChevalierDto createChevalier(InChevalierDto inChevalierDto);

    // UTILS

    List<String> getAllTitres();

    boolean isTitreExists(String titre);
}
