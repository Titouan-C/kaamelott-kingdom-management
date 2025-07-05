package com.example.kaamelott.features.quetes.services;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.features.quetes.dtos.out.OutParticipantDto;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing Quêtes in the Kaamelott application.
 */
public interface IQueteService {

    OutPaginatedDataDto<List<OutParticipantDto>> getParticipants(
            UUID queteId,
            Integer cursor,
            Integer pageSize
    );

}
