package com.example.kaamelott.features.quetes.services;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.common.utils.PaginationUtils;
import com.example.kaamelott.features.quetes.dtos.out.OutParticipantDto;
import com.example.kaamelott.features.quetes.entities.ParticipationQueteEntity;
import com.example.kaamelott.features.quetes.repositories.QueteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QueteService implements IQueteService{

    private final QueteRepository queteRepository;

    @Override
    public OutPaginatedDataDto<List<OutParticipantDto>> getParticipants(UUID queteId, Integer cursor, Integer pageSize) {
        return PaginationUtils.paginate(
                cursor,
                pageSize,
                (pageable) -> queteRepository.findParticipantsByQueteId(queteId, pageable),
                this::toParticipantDto
        );
    }

    private OutParticipantDto toParticipantDto(ParticipationQueteEntity participationQueteEntity) {
        return new OutParticipantDto(
                participationQueteEntity.getId(),
                participationQueteEntity.getChevalier().getNom(),
                participationQueteEntity.getRole().getLabel(),
                participationQueteEntity.getStatutParticipation().getLabel()
        );
    }
}
