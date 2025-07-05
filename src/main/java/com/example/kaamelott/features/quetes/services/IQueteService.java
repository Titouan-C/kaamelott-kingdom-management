package com.example.kaamelott.features.quetes.services;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.features.quetes.dtos.in.InParticipantDto;
import com.example.kaamelott.features.quetes.dtos.out.OutParticipantDto;
import com.example.kaamelott.features.quetes.dtos.out.OutQueteDto;
import com.example.kaamelott.features.quetes.entities.QueteEntity;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing Quêtes in the Kaamelott application.
 */
public interface IQueteService {

    QueteEntity getQueteById(UUID queteId);

    OutPaginatedDataDto<List<OutParticipantDto>> getParticipants(
            UUID queteId,
            Integer cursor,
            Integer pageSize
    );

    OutParticipantDto assignChevalierToQuete(
            UUID queteId,
            InParticipantDto inParticipantDto
    );

    OutPaginatedDataDto<List<OutQueteDto>> getQuetesDifficulteAberrante(
            Integer cursor,
            Integer pageSize
    );

}
