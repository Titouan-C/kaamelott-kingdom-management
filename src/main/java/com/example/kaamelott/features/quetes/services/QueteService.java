package com.example.kaamelott.features.quetes.services;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.common.exceptions.http.NotFoundException;
import com.example.kaamelott.common.utils.PaginationUtils;
import com.example.kaamelott.features.chevaliers.services.ChevalierService;
import com.example.kaamelott.features.quetes.dtos.in.InParticipantDto;
import com.example.kaamelott.features.quetes.dtos.out.OutParticipantDto;
import com.example.kaamelott.features.quetes.dtos.out.OutQueteDto;
import com.example.kaamelott.features.quetes.entities.ParticipationQueteEntity;
import com.example.kaamelott.features.quetes.entities.QueteEntity;
import com.example.kaamelott.features.quetes.enumerations.ParticipationQueteRole;
import com.example.kaamelott.features.quetes.enumerations.StatutParticipationQuete;
import com.example.kaamelott.features.quetes.repositories.ParticipationQueteRepository;
import com.example.kaamelott.features.quetes.repositories.QueteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QueteService implements IQueteService{

    private final QueteRepository queteRepository;
    private final ParticipationQueteRepository participationQueteRepository;
    private final ChevalierService chevalierService;

    @Override
    public QueteEntity getQueteById(UUID queteId) {
        return queteRepository.findById(queteId)
                .orElseThrow(() -> new NotFoundException("Quête non trouvée avec l'ID : " + queteId));
    }

    @Override
    public OutPaginatedDataDto<List<OutParticipantDto>> getParticipants(UUID queteId, Integer cursor, Integer pageSize) {
        return PaginationUtils.paginate(
                cursor,
                pageSize,
                (pageable) -> queteRepository.findParticipantsByQueteId(queteId, pageable),
                this::toParticipantDto
        );
    }

    @Override
    @Transactional
    public OutParticipantDto assignChevalierToQuete(UUID queteId, InParticipantDto inParticipantDto) {
        ParticipationQueteEntity participationQueteEntity = toParticipationQueteEntity(queteId, inParticipantDto);
        ParticipationQueteEntity savedParticipation = participationQueteRepository.save(participationQueteEntity);
        return toParticipantDto(savedParticipation);
    }

    @Override
    public OutPaginatedDataDto<List<OutQueteDto>> getQuetesDifficulteAberrante(Integer cursor, Integer pageSize) {
        return PaginationUtils.paginate(
                cursor,
                pageSize,
                (pageable) -> queteRepository.findQuetesDifficulteAberrante(
                        StatutParticipationQuete.getFinishedStatues(),
                        pageable
                ),
                this::toDto
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

    private ParticipationQueteEntity toParticipationQueteEntity(UUID queteId, InParticipantDto inParticipantDto) {
        return new ParticipationQueteEntity(
                getQueteById(queteId),
                chevalierService.getChevalierById(inParticipantDto.getId_chevalier()),
                ParticipationQueteRole.fromLabel(inParticipantDto.getRole()),
                StatutParticipationQuete.fromLabel(inParticipantDto.getStatus())
        );
    }

    private OutQueteDto toDto(QueteEntity queteEntity) {
        return new OutQueteDto(
                queteEntity.getId(),
                queteEntity.getNom_quete(),
                queteEntity.getDescription(),
                queteEntity.getDifficulte().getLabel(),
                queteEntity.getDateAssignation(),
                queteEntity.getDateEcheance()
        );
    }
}
