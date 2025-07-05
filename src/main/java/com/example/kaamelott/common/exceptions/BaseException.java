package com.example.kaamelott.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Base exception class for all custom exceptions.
 * Provides a structured way to handle errors with an error code and an HTTP status.
 */
@Getter
public class BaseException extends RuntimeException {

  private final String errorCode;
  private final HttpStatus httpStatus;

  /**
   * Constructor for BaseException.
   *
   * @param message The exception message to provide context to the user or log.
   * @param errorCode The unique error code for this exception.
   * @param httpStatus The HTTP status associated with this error.
   */
  protected BaseException(
    String message,
    String errorCode,
    HttpStatus httpStatus
  ) {
    super(message);
    this.errorCode = errorCode;
    this.httpStatus = httpStatus;
  }
}
