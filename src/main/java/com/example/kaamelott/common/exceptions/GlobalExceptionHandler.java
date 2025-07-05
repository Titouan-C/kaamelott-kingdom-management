package com.example.kaamelott.common.exceptions;

import com.example.kaamelott.common.dtos.OutResponseDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * Global exception handler for all exceptions in the application.
 * This class handles exceptions globally, providing a unified response structure for all error scenarios.
 * It handles specific exceptions (such as BadRequestException, NotFoundException) and general exceptions,
 * and maps them to appropriate HTTP status codes and error messages.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles all exceptions that are instances of BaseException.
   * This method constructs a response entity containing the exception message and HTTP status.
   *
   * @param ex The BaseException instance that was thrown.
   * @return A ResponseEntity containing the OutResponseDto with the error message and HTTP status.
   */
  @ExceptionHandler(BaseException.class)
  public ResponseEntity<OutResponseDto<?>> handleBaseException(
    BaseException ex
  ) {
    return buildErrorResponse(ex.getMessage(), ex.getHttpStatus());
  }

  /**
   * Handles MethodArgumentNotValidException, which occurs when a method argument fails validation.
   *
   * @param ex The MethodArgumentNotValidException that was thrown.
   * @return A ResponseEntity containing the OutResponseDto with the error message and a 400 HTTP status.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OutResponseDto<?>> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException ex
    ) {
        String message = String.format(
        "Entrée invalide : %s",
        ex.getBindingResult().getFieldError().getDefaultMessage()
        );
        return buildErrorResponse(message, HttpStatus.BAD_REQUEST);
    }

  /**
   * Handles IllegalArgumentException, which typically occurs when invalid input is provided.
   * This method constructs a detailed error message and returns a ResponseEntity with a 400 Bad Request status.
   *
   * @param ex The IllegalArgumentException that was thrown.
   * @return A ResponseEntity containing the OutResponseDto with the error message and a 400 HTTP status.
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<OutResponseDto<?>> handleIllegalArgumentException(
    Exception ex
  ) {
    String message = String.format(
      "Entrée invalide : %s",
      ex.getMessage()
    );
    return buildErrorResponse(message, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles exceptions related to missing request bodies (HttpMessageNotReadableException and MissingServletRequestPartException).
   * This method constructs a response with an error message indicating the missing request body, returning a 400 Bad Request status.
   *
   * @param ex The exception related to missing request body.
   * @return A ResponseEntity containing the OutResponseDto with the error message and a 400 HTTP status.
   */
  @ExceptionHandler(
    {
      HttpMessageNotReadableException.class,
      MissingServletRequestPartException.class,
    }
  )
  public ResponseEntity<OutResponseDto<?>> handleMissingRequestBody(
    Exception ex
  ) {
    System.out.printf(
      "Exception: %s ; Message: %s%n",
      ex.getClass().getSimpleName(),
      ex.getMessage()
    );

    String message;

    if (ex.getCause() instanceof InvalidFormatException) {
      message = String.format(
        "Corps de requête invalide : %s",
        ex.getMessage()
      );
    } else {
      message = "Un corps de requête requis est manquant ou invalide dans la requête.";
    }

    return buildErrorResponse(message, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles HttpRequestMethodNotSupportedException, which occurs when a client sends a request with
   * an unsupported HTTP method for a specific endpoint.
   * This method returns a response with an error message indicating the unsupported method,
   * along with a 405 Method Not Allowed status.
   *
   * @param ex The HttpRequestMethodNotSupportedException that was thrown.
   * @return A ResponseEntity containing the OutResponseDto with the error message and a 405 HTTP status.
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<OutResponseDto<?>> handleMethodNotSupported(
    HttpRequestMethodNotSupportedException ex
  ) {
    String message = String.format(
      "La méthode de requête '%s' n'est pas supportée pour cet endpoint.",
      ex.getMethod()
    );
    return buildErrorResponse(message, HttpStatus.METHOD_NOT_ALLOWED);
  }

  /**
   * Handles MissingRequestHeaderException, which occurs when a required request header is missing.
   * This method returns a response with an error message indicating the missing header, with a 400 Bad Request status.
   *
   * @param ex The MissingRequestHeaderException that was thrown.
   * @return A ResponseEntity containing the OutResponseDto with the error message and a 400 HTTP status.
   */
  @ExceptionHandler(MissingRequestHeaderException.class)
  public ResponseEntity<OutResponseDto<?>> handleMissingRequestHeader(
    MissingRequestHeaderException ex
  ) {
    String message = String.format(
      "Un en-tête de requête requis '%s' est manquant dans la requête",
      ex.getHeaderName()
    );
    return buildErrorResponse(message, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles MissingServletRequestParameterException, which occurs when a required request parameter is missing.
   * This method returns a response with an error message indicating the missing parameter, with a 400 Bad Request status.
   *
   * @param ex The MissingServletRequestParameterException that was thrown.
   * @return A ResponseEntity containing the OutResponseDto with the error message and a 400 HTTP status.
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<OutResponseDto<?>> handleMissingRequestParameter(
    MissingServletRequestParameterException ex
  ) {
    String message = String.format(
      "Un paramètre de requête requis '%s' est manquant dans la requête",
      ex.getParameterName()
    );
    return buildErrorResponse(message, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles any other general exceptions that are not specifically caught by other handlers.
   * This method constructs a response with an error message and a 500 Internal Server Error status.
   *
   * @param ex The general Exception that was thrown.
   * @return A ResponseEntity containing the OutResponseDto with the error message and a 500 HTTP status.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<OutResponseDto<?>> handleGeneralException(
    Exception ex
  ) {
    String message = String.format(
      "Une erreur inattendue est survenue : %s",
      ex.getMessage()
    );
    return buildErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Helper method to build a response entity with a given error message and HTTP status.
   *
   * @param message The error message to be included in the response.
   * @param status The HTTP status to be associated with the response.
   * @return A ResponseEntity containing the OutResponseDto with the error message and HTTP status.
   */
  private ResponseEntity<OutResponseDto<?>> buildErrorResponse(
    String message,
    HttpStatus status
  ) {
    return new ResponseEntity<>(new OutResponseDto<>(false, message), status);
  }
}
