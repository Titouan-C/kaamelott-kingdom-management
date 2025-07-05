package com.example.kaamelott.common.exceptions.http;

import com.example.kaamelott.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a bad request is made, such as invalid or missing fields in the request.
 * This exception includes a default message and error code for the 400 Bad Request HTTP status.
 */
public class BadRequestException extends BaseException {

  // Default error code specific to the BadRequestException.
  private static final String DEFAULT_ERROR_CODE = "BAD_REQUEST";

  /**
   * Default constructor for BadRequestException.
   * Uses the default localized message for bad request errors.
   */
  public BadRequestException() {
    this("Mauvaise requête");
  }

  /**
   * Constructor for BadRequestException with a custom message.
   * Allows setting a specific message while keeping the default error code and status.
   *
   * @param message Custom message to describe the bad request error.
   */
  public BadRequestException(String message) {
    super(message, DEFAULT_ERROR_CODE, HttpStatus.BAD_REQUEST);
  }

  /**
   * Constructor for BadRequestException with both a custom message and custom error code.
   * Provides full flexibility to customize the error message and error code while using the 400 status.
   *
   * @param message Custom message to describe the bad request error.
   * @param errorCode A custom error code specific to this exception.
   */
  public BadRequestException(String message, String errorCode) {
    super(message, errorCode, HttpStatus.BAD_REQUEST);
  }
}
