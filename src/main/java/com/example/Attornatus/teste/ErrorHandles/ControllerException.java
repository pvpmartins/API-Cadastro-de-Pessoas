package com.example.Attornatus.teste.ErrorHandles;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerException extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<Map<String, String>> handle(ConstraintViolationException exception) {

        List<Map<String, String>> errors = new ArrayList<>();

        exception.getConstraintViolations().forEach(e->{
            Map<String, String> exceptions= new HashMap<>();
            exceptions.put("ErrorMessage: ", e.getMessage()) ;
            errors.add(exceptions);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { NaoEncontradoException.class })
    public String handle(NaoEncontradoException ex) {

        return ex.getMessage();
    }
}
