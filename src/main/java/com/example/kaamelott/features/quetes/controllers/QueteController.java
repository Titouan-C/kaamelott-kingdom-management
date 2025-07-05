package com.example.kaamelott.features.quetes.controllers;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.common.dtos.OutResponseDto;
import com.example.kaamelott.common.utils.PaginationUtils;
import com.example.kaamelott.common.utils.ValidationUtils;
import com.example.kaamelott.features.quetes.dtos.in.InParticipantDto;
import com.example.kaamelott.features.quetes.dtos.out.OutParticipantDto;
import com.example.kaamelott.features.quetes.dtos.out.OutQueteDto;
import com.example.kaamelott.features.quetes.services.IQueteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for managing Quêtes in the Kaamelott application.
 */
@RestController
@RequestMapping("quetes")
@AllArgsConstructor
public class QueteController {

    private final IQueteService queteService;

    /**
     * Retrieves a paginated list of participants for a specific Quête.
     *
     * @param queteId the ID of the Quête
     * @param cursor the current page index, defaults to 0 if not provided
     * @param pageSize the number of items per page, defaults to 10 if not provided
     * @return a ResponseEntity containing the paginated list of participants
     */
    @GetMapping("{queteId}/participants")
    public ResponseEntity<OutResponseDto<OutPaginatedDataDto<List<OutParticipantDto>>>> getParticipants(
            @PathVariable String queteId,
            @RequestParam(name = PaginationUtils.PAGE, defaultValue = PaginationUtils.DEFAULT_CURSOR) String cursor,
            @RequestParam(name = PaginationUtils.LIMIT, defaultValue = PaginationUtils.DEFAULT_PAGE_SIZE) String pageSize
    ) {
        UUID queteUuid = ValidationUtils.validateAndParseUUID(queteId, "queteId");
        Integer cursorValue = ValidationUtils.validateAndParseInt(cursor, PaginationUtils.PAGE);
        Integer pageSizeValue = ValidationUtils.validateAndParseInt(pageSize, PaginationUtils.LIMIT);

        OutPaginatedDataDto<List<OutParticipantDto>> participantsList = queteService.getParticipants(
                queteUuid,
                cursorValue,
                pageSizeValue
        );
        OutResponseDto<OutPaginatedDataDto<List<OutParticipantDto>>> response = new OutResponseDto<>(
                participantsList,
                true,
                "Participants récupérés avec succès"
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Assigns a Chevalier to a Quête.
     *
     * @param queteId the ID of the Quête
     * @param inParticipantDto the details of the participant to assign
     * @return a ResponseEntity containing the assigned participant details
     */
    @PostMapping("{queteId}/assigner-chevalier")
    public ResponseEntity<OutResponseDto<OutParticipantDto>> assignChevalierToQuete(
            @PathVariable String queteId,
            @RequestBody InParticipantDto inParticipantDto
    ) {
        UUID queteUuid = ValidationUtils.validateAndParseUUID(queteId, "queteId");

        OutParticipantDto participantDto = queteService.assignChevalierToQuete(queteUuid, inParticipantDto);
        OutResponseDto<OutParticipantDto> response = new OutResponseDto<>(
                participantDto,
                true,
                "Chevalier assigné à la quête avec succès"
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("difficulte-aberrante")
    public ResponseEntity<OutResponseDto<OutPaginatedDataDto<List<OutQueteDto>>>> getQuetesDifficulteAberrante(
            @RequestParam(name = PaginationUtils.PAGE, defaultValue = PaginationUtils.DEFAULT_CURSOR) String cursor,
            @RequestParam(name = PaginationUtils.LIMIT, defaultValue = PaginationUtils.DEFAULT_PAGE_SIZE) String pageSize
    ) {
        Integer cursorValue = ValidationUtils.validateAndParseInt(cursor, PaginationUtils.PAGE);
        Integer pageSizeValue = ValidationUtils.validateAndParseInt(pageSize, PaginationUtils.LIMIT);

        OutPaginatedDataDto<List<OutQueteDto>> quetes = queteService.getQuetesDifficulteAberrante(
                cursorValue,
                pageSizeValue
        );
        OutResponseDto<OutPaginatedDataDto<List<OutQueteDto>>> response = new OutResponseDto<>(
                quetes,
                true,
                "Quêtes avec difficulté aberrante récupérées avec succès"
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
