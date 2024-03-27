package es.retail.store.infrastructure.api.rest.config;

import java.util.Optional;

import javax.naming.ServiceUnavailableException;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	   * Get the ResponseEntity for this exception.
	   * 
	   * @param e The ResourceNotFoundException
	   * @return The ResponseEntity
	   */
	  @ExceptionHandler(ResourceNotFoundException.class)
	  public ResponseEntity<ErrorDto> notFoundException(final ResourceNotFoundException e) {
	    return createError(e, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
	  }

	  
	  /**
	   * Get the ResponseEntity for this exception.
	   * 
	   * @param e The EntityNotFoundException
	   * @return The ResponseEntity
	   */
	  @ExceptionHandler(EntityNotFoundException.class)
	  public ResponseEntity<ErrorDto> entityNotFoundException(final EntityNotFoundException e) {
	    return createError(e, HttpStatus.NOT_FOUND,
	        HttpStatus.NOT_FOUND.getReasonPhrase());
	  }

	  /**
	   * Get the ResponseEntity for this exception.
	   * 
	   * @param e The UnsupportedOperationException
	   * @return The ResponseEntity
	   */
	  @ExceptionHandler(UnsupportedOperationException.class)
	  public ResponseEntity<ErrorDto> unsupportedOperationException(final UnsupportedOperationException e) {
	    return createError(e, HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase());
	  }

	  /**
	   * Get the ResponseEntity for this exception.
	   * 
	   * @param e The IllegalArgumentException
	   * @return The ResponseEntity
	   */
	  @ExceptionHandler(IllegalArgumentException.class)
	  public ResponseEntity<ErrorDto> argumentException(final IllegalArgumentException e) {
	    return createError(e, HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
	  }

	  /**
	   * Get the ResponseEntity for this exception.
	   * 
	   * @param e The ConstraintViolationException
	   * @return The ResponseEntity
	   */
	  @ExceptionHandler(ConstraintViolationException.class)
	  public ResponseEntity<ErrorDto> constraintViolationException(final ConstraintViolationException e) {
	    return createError(e, HttpStatus.BAD_REQUEST, e.toString());
	  }

	  /**
	   * Get the ResponseEntity for this exception.
	   * 
	   * @param e The ServiceUnavailableException
	   * @return The ResponseEntity
	   */
	  @ExceptionHandler(ServiceUnavailableException.class)
	  public ResponseEntity<ErrorDto> serviceUnavailableException(final ServiceUnavailableException e) {
	    return createError(e, HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
	  }

	  /**
	   * Creates the ResponseEntity.
	   * 
	   * @param exception The Exception
	   * @param httpStatus The HttpStatus
	   * @param type The type
	   * @return The ResponseEntity
	   */
	  private ResponseEntity<ErrorDto> createError(final Exception exception, final HttpStatus httpStatus,
	      final String type) {
	    ErrorDto error = getErrorDto(exception, httpStatus, type);

	    return new ResponseEntity<>(error, httpStatus);
	  }

	  /**
	   * Creates the ErrorDto.
	   * 
	   * @param exception The Exception
	   * @param httpStatus The HttpStatus
	   * @param type The type
	   * @return The ErrorDto
	   */
	  private ErrorDto getErrorDto(Exception exception, HttpStatus httpStatus, String type) {
	    final String message =
	        Optional.ofNullable(exception.getMessage()).orElse(exception.getClass().getSimpleName());

	    return new ErrorDto(httpStatus.value(), type, message);
	  }
}
