package com.example.kaamelott.features.chevaliers.services;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.common.exceptions.http.NotFoundException;
import com.example.kaamelott.common.utils.PaginationUtils;
import com.example.kaamelott.features.chevaliers.dtos.in.InChevalierDto;
import com.example.kaamelott.features.chevaliers.dtos.out.OutChevalierDto;
import com.example.kaamelott.features.chevaliers.entities.ChevalierEntity;
import com.example.kaamelott.features.chevaliers.entities.ChevalierTitreEntity;
import com.example.kaamelott.features.chevaliers.enumerations.CaracteristiquePrincipale;
import com.example.kaamelott.features.chevaliers.repositories.ChevalierRepository;
import com.example.kaamelott.features.chevaliers.repositories.ChevalierTitreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChevalierService implements IChevalierService {

    private final ChevalierRepository chevalierRepository;
    private final ChevalierTitreRepository chevalierTitreRepository;

    @Override
    public OutPaginatedDataDto<List<OutChevalierDto>> getAll(Integer cursor, Integer pageSize) {
        return PaginationUtils.paginate(
                cursor,
                pageSize,
                chevalierRepository::findAll,
                this::toDto
        );
    }

    @Override
    public OutChevalierDto createChevalier(InChevalierDto inChevalierDto) {
        ChevalierEntity chevalierEntity = toEntity(inChevalierDto);
        // New variable to get the ID after saving
        ChevalierEntity savedChevalier = chevalierRepository.save(chevalierEntity);
        return toDto(savedChevalier);
    }

    @Override
    public List<String> getAllTitres() {
        return chevalierTitreRepository.findAll()
                .stream()
                .map(ChevalierTitreEntity::getTitre)
                .toList();
    }

    @Override
    public boolean isTitreExists(String titre) {
        return chevalierTitreRepository.existsByTitre(titre);
    }

    private ChevalierEntity toEntity(InChevalierDto inChevalierDto) {
        ChevalierTitreEntity titreEntity = chevalierTitreRepository.findByTitre(inChevalierDto.getTitre())
                .orElseThrow(() -> new NotFoundException("Titre non trouvé: " + inChevalierDto.getTitre()));

        return new ChevalierEntity(
                inChevalierDto.getNom(),
                titreEntity,
                CaracteristiquePrincipale.fromLabel(inChevalierDto.getCaracteristique_principale()),
                inChevalierDto.getNiveau_bravoure()
        );
    }

    private OutChevalierDto toDto(ChevalierEntity entity) {
        return new OutChevalierDto(
                entity.getId(),
                entity.getNom(),
                entity.getTitre().getTitre(),
                entity.getCaracteristiquePrincipale().getLabel(),
                entity.getNiveauBravoure()
        );
    }
}
