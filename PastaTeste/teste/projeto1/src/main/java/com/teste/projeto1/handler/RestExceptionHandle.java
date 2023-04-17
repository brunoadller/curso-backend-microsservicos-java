package com.teste.projeto1.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teste.projeto1.model.Error.ErrorMessage;
import com.teste.projeto1.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandle {
  
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
    ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
}
