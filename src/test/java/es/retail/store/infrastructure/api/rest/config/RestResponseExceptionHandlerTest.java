package es.retail.store.infrastructure.api.rest.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import javax.naming.ServiceUnavailableException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

class RestResponseExceptionHandlerTest {

	private RestResponseExceptionHandler restResponseExceptionHandler = new RestResponseExceptionHandler();
	private ResourceNotFoundException notFoundException = Mockito.mock(ResourceNotFoundException.class);
	private EntityNotFoundException entityNotFoundException = Mockito.mock(EntityNotFoundException.class);
	private UnsupportedOperationException unsupportedOperationException = Mockito.mock(UnsupportedOperationException.class);
	private IllegalArgumentException illegalArgumentException = Mockito.mock(IllegalArgumentException.class);
	private ConstraintViolationException constraintViolationException =  Mockito.mock(ConstraintViolationException.class);
	private ServiceUnavailableException serviceUnavailableException =  Mockito.mock(ServiceUnavailableException.class); 
	
	@Test
	public void notFoundException() {
		when(notFoundException.getMessage()).thenReturn("notFoundException");
	    ResponseEntity<ErrorDto> result = restResponseExceptionHandler.notFoundException(notFoundException);
	    ErrorDto errorDto = result.getBody();
	    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	    assertEquals(HttpStatus.NOT_FOUND.value(), errorDto.code());
	    assertEquals(notFoundException.getMessage(), errorDto.description());
	}
	
	@Test
	public void entityNotFoundException() {
		when(entityNotFoundException.getMessage()).thenReturn("entityNotFoundException");
	    ResponseEntity<ErrorDto> result = restResponseExceptionHandler.entityNotFoundException(entityNotFoundException);
	    ErrorDto errorDto = result.getBody();
	    assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	    assertEquals(HttpStatus.NOT_FOUND.value(), errorDto.code());
	    assertEquals(entityNotFoundException.getMessage(), errorDto.description());
	}

	@Test
	public void unsupportedOperationException() {		
	    when(unsupportedOperationException.getMessage()).thenReturn("unsupportedOperationException");
	    ResponseEntity<ErrorDto> result = restResponseExceptionHandler.unsupportedOperationException(unsupportedOperationException);
	    ErrorDto errorDto = result.getBody();
	    assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
	    assertEquals(HttpStatus.FORBIDDEN.value(), errorDto.code());
	    assertEquals(unsupportedOperationException.getMessage(), errorDto.description());
	}
	
	@Test
	public void IllegalArgumentException() {
		when(illegalArgumentException.getMessage()).thenReturn("illegalArgumentException");
	    ResponseEntity<ErrorDto> result = restResponseExceptionHandler.argumentException(illegalArgumentException);
	    ErrorDto errorDto = result.getBody();
	    assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	    assertEquals(HttpStatus.BAD_REQUEST.value(), errorDto.code());
	    assertEquals(illegalArgumentException.getMessage(), errorDto.description());
	}
	  
	@Test
	public void constraintViolationException() {
	    when(constraintViolationException.getMessage()).thenReturn("constraintViolationException");
	    ResponseEntity<ErrorDto> result = restResponseExceptionHandler.constraintViolationException(constraintViolationException);
	    ErrorDto errorDto = result.getBody();
	    assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	    assertEquals(HttpStatus.BAD_REQUEST.value(), errorDto.code());
	    assertEquals(constraintViolationException.getMessage(), errorDto.description());
	}
	  
	@Test
	public void serviceUnavailableException() {
	    when(serviceUnavailableException.getMessage()).thenReturn("serviceUnavailableException");
	    ResponseEntity<ErrorDto> result = restResponseExceptionHandler.serviceUnavailableException(serviceUnavailableException);
	    ErrorDto errorDto = result.getBody();
	    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, result.getStatusCode());
	    assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(), errorDto.code());
	    assertEquals(serviceUnavailableException.getMessage(), errorDto.description());
	}
	
}
