package com.example.kaamelott.common.utils;

import com.example.kaamelott.common.exceptions.http.BadRequestException;
import java.util.Collection;
import java.util.UUID;

/**
 * Utility class for data validation.
 */
public class ValidationUtils {

  /**
   * Validates and parses a string to an integer.
   *
   * @param stringNumber String to be validated and parsed
   * @param fieldName Name of the field that was provided
   * @return Integer if validation is successful
   * @throws BadRequestException if the string cannot be parsed
   */
  public static Integer validateAndParseInt(
    String stringNumber,
    String fieldName
  ) {
    validateNotNullAndNotEmpty(stringNumber, fieldName);

    try {
      return Integer.parseInt(stringNumber);
    } catch (NumberFormatException e) {
      throw new BadRequestException(
        String.format(
            "La valeur du champ '%s' est invalide",
          fieldName
        )
      );
    }
  }

  /**
   * Validates that a required field is not null and not empty.
   *
   * @param fieldValue The value to check.
   * @param fieldName  The name of the field for error messaging.
   * @throws BadRequestException if the field is null.
   */
  public static void validateNotNullAndNotEmpty(
    Object fieldValue,
    String fieldName
  ) {
    if (fieldValue == null) {
      throw new BadRequestException(
        String.format("La valeur du champ '%s' ne peut pas être nulle"
                , fieldName)
      );
    }

    if (fieldValue instanceof String) {
      if (((String) fieldValue).trim().isEmpty()) {
        throw new BadRequestException(
          String.format(
            "La valeur du champ '%s' ne peut pas être vide",
            fieldName
          )
        );
      }
    } else if (fieldValue instanceof Collection) {
      if (((Collection<?>) fieldValue).isEmpty()) {
        throw new BadRequestException(
          String.format(
            "La collection du champ '%s' ne peut pas être vide",
            fieldName
          )
        );
      }
    }
  }
}
