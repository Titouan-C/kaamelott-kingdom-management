package com.example.kaamelott.common.exceptions.http;

import com.example.kaamelott.common.exceptions.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a resource is not found, such as when querying a non-existent entity.
 * This exception includes a default message and error code for the 404 Not Found HTTP status.
 */
public class NotFoundException extends BaseException {

  // Default error code specific to the NotFoundException.
  private static final String DEFAULT_ERROR_CODE = "NOT_FOUND";

  /**
   * Default constructor for NotFoundException.
   * Uses the default localized message for "not found" errors.
   */
  public NotFoundException() {
    this("Non trouvé");
  }

  /**
   * Constructor for NotFoundException with a custom message.
   * Allows setting a specific message while keeping the default error code and status.
   *
   * @param message Custom message to describe the "not found" error.
   */
  public NotFoundException(String message) {
    super(message, DEFAULT_ERROR_CODE, HttpStatus.NOT_FOUND);
  }

  /**
   * Constructor for NotFoundException with both a custom message and custom error code.
   * Provides full flexibility to customize the error message and error code while using the 404 status.
   *
   * @param message Custom message to describe the "not found" error.
   * @param errorCode A custom error code specific to this exception.
   */
  public NotFoundException(String message, String errorCode) {
    super(message, errorCode, HttpStatus.NOT_FOUND);
  }
}
