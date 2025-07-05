package com.example.kaamelott.features.chevaliers.controllers;

import com.example.kaamelott.common.dtos.OutPaginatedDataDto;
import com.example.kaamelott.common.dtos.OutResponseDto;
import com.example.kaamelott.common.exceptions.http.BadRequestException;
import com.example.kaamelott.common.utils.PaginationUtils;
import com.example.kaamelott.common.utils.ValidationUtils;
import com.example.kaamelott.features.chevaliers.dtos.in.InChevalierDto;
import com.example.kaamelott.features.chevaliers.dtos.out.OutChevalierDto;
import com.example.kaamelott.features.chevaliers.services.IChevalierService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Chevaliers in the Kaamelott application.
 */
@RestController
@RequestMapping("chevaliers")
@AllArgsConstructor
public class ChevalierController {

    private final IChevalierService chevalierService;

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

}
