package com.example.kaamelott.features.chevaliers.controllers;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.common.dtos.OutResponseDto;
import com.example.kaamelott.common.exceptions.http.BadRequestException;
import com.example.kaamelott.common.exceptions.http.NotFoundException;
import com.example.kaamelott.common.utils.PaginationUtils;
import com.example.kaamelott.common.utils.ValidationUtils;
import com.example.kaamelott.features.chevaliers.dtos.in.InChevalierDto;
import com.example.kaamelott.features.chevaliers.dtos.out.OutChevalierDto;
import com.example.kaamelott.features.chevaliers.dtos.out.OutChevalierQueteDto;
import com.example.kaamelott.features.chevaliers.services.IChevalierService;
import com.example.kaamelott.features.quetes.services.IQueteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for managing Chevaliers in the Kaamelott application.
 */
@RestController
@RequestMapping("chevaliers")
@AllArgsConstructor
public class ChevalierController {

    private final IChevalierService chevalierService;
    private final IQueteService queteService;

    /**
     * Retrieves a paginated list of Chevaliers.
     *
     * @param cursor the current page index, defaults to 0 if not provided
     * @param pageSize the number of items per page, defaults to 10 if not provided
     * @return a ResponseEntity containing the paginated list of Chevaliers
     */
    @GetMapping("")
    public ResponseEntity<OutResponseDto<OutPaginatedDataDto<List<OutChevalierDto>>>> getChevaliers(
            @RequestParam(name = PaginationUtils.PAGE, defaultValue = PaginationUtils.DEFAULT_CURSOR) String cursor,
            @RequestParam(name = PaginationUtils.LIMIT, defaultValue = PaginationUtils.DEFAULT_PAGE_SIZE) String pageSize
    ) {
        Integer cursorValue = ValidationUtils.validateAndParseInt(cursor, PaginationUtils.PAGE);
        Integer pageSizeValue = ValidationUtils.validateAndParseInt(pageSize, PaginationUtils.LIMIT);

        OutPaginatedDataDto<List<OutChevalierDto>> chevalierList = chevalierService.getAll(
                cursorValue,
                pageSizeValue
        );
        OutResponseDto<OutPaginatedDataDto<List<OutChevalierDto>>> response = new OutResponseDto<>(
                chevalierList,
                true,
                "Chevaliers récupérés avec succès"
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Creates a new Chevalier.
     *
     * @param inChevalierDto the DTO containing the details of the Chevalier to be created
     * @return a ResponseEntity containing the created Chevalier DTO
     */
    @PostMapping("")
    public ResponseEntity<OutResponseDto<OutChevalierDto>> addChevalier(
            @Valid  @RequestBody InChevalierDto inChevalierDto
    ) {
        // Validate the chevalier titre
        if (!chevalierService.isTitreExists(inChevalierDto.getTitre())) {
            throw new BadRequestException(String.format(
                    "Le titre '%s' n'existe pas. Les titres disponibles sont : %s",
                    inChevalierDto.getTitre(),
                    chevalierService.getAllTitres()
            ));
        }

        // Create the chevalier entity from the DTO
        OutChevalierDto outChevalierDto = chevalierService.createChevalier(inChevalierDto);
        OutResponseDto<OutChevalierDto> response = new OutResponseDto<>(
                outChevalierDto,
                true,
                "Chevalier créé avec succès"
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves the list of ongoing quests for a specific Chevalier.
     *
     * @param idChevalier the ID of the Chevalier whose ongoing quests are to be retrieved
     * @param cursor the current page index, defaults to 0 if not provided
     * @param pageSize the number of items per page, defaults to 10 if not provided
     * @return a ResponseEntity containing the paginated list of ongoing quests for the Chevalier
     */
    @GetMapping("{idChevalier}/quetes-en-cours")
    public ResponseEntity<OutResponseDto<OutPaginatedDataDto<List<OutChevalierQueteDto>>>> getQuetesEnCours(
            @PathVariable String idChevalier,
            @RequestParam(name = PaginationUtils.PAGE, defaultValue = PaginationUtils.DEFAULT_CURSOR) String cursor,
            @RequestParam(name = PaginationUtils.LIMIT, defaultValue = PaginationUtils.DEFAULT_PAGE_SIZE) String pageSize
    ) {
        UUID chevalierId = ValidationUtils.validateAndParseUUID(idChevalier, "idChevalier");
        Integer cursorValue = ValidationUtils.validateAndParseInt(cursor, PaginationUtils.PAGE);
        Integer pageSizeValue = ValidationUtils.validateAndParseInt(pageSize, PaginationUtils.LIMIT);

        OutPaginatedDataDto<List<OutChevalierQueteDto>> quetesEnCours = chevalierService.getQuetesEnCours(chevalierId, cursorValue, pageSizeValue);
        OutResponseDto<OutPaginatedDataDto<List<OutChevalierQueteDto>>> response = new OutResponseDto<>(
                quetesEnCours,
                true,
                "Quêtes en cours récupérées avec succès"
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{idChevalier}/retirer-quete/{idQuete}")
    public ResponseEntity<OutResponseDto<Void>> retirerQuete(
            @PathVariable String idChevalier,
            @PathVariable String idQuete
    ) {
        UUID chevalierId = ValidationUtils.validateAndParseUUID(idChevalier, "idChevalier");
        UUID queteId = ValidationUtils.validateAndParseUUID(idQuete, "idQuete");

        if (!chevalierService.isChevalierExists(chevalierId)) {
            throw new NotFoundException("Chevalier non trouvé ou supprimé avec l'ID : " + chevalierId);
        }
        if(!queteService.isQueteExists(queteId)) {
            throw new NotFoundException("Quête non trouvée ou supprimée avec l'ID : " + queteId);
        }

        chevalierService.retirerQuete(chevalierId, queteId);
        OutResponseDto<Void> response = new OutResponseDto<>(
                null,
                true,
                "Chevalier retiré de la quête avec succès"
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
