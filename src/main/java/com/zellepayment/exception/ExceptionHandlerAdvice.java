package com.zellepayment.exception;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(UsersException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserDetailsNotFound(UsersException e, HttpServletRequest request){
      log.debug("Unhandled UserDetailNotFoundException:{}",e.getMessage());
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setErrorMessage(e.getMessage());
      errorResponse.setUrl(e.getMessage());
      return errorResponse;
    }
    public ErrorResponse handleGenericException(Exception ex,HttpServletRequest request){
        log.error("Unhandled exception caught:",ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setUrl(ex.getMessage());
        return errorResponse;
    }

}
