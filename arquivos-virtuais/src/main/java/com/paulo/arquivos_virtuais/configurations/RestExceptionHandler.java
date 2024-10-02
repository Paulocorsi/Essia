package com.paulo.arquivos_virtuais.configurations;

import com.paulo.arquivos_virtuais.exceptions.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleDataNotFoundException(DataNotFoundException e) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }
}
