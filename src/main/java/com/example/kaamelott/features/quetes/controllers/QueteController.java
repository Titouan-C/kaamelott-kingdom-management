package com.example.kaamelott.features.quetes.controllers;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.common.dtos.OutResponseDto;
import com.example.kaamelott.common.utils.PaginationUtils;
import com.example.kaamelott.common.utils.ValidationUtils;
import com.example.kaamelott.features.quetes.dtos.out.OutParticipantDto;
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

}
