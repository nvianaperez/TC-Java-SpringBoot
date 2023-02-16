package com.example.apollofy.rest.exception;

import com.example.apollofy.utilites.ElementNotFoundException;
import com.example.apollofy.utilites.PlaylistAlreadyExistsException;
import lombok.Data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlingAdvice {
    @ResponseBody
    @ExceptionHandler({PlaylistAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorMessage badRequestHandler(Exception ex) {
        return new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseBody
    @ExceptionHandler({ElementNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorMessage notFoundHandler(Exception ex) {
        return new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @Data
    class ErrorMessage {

        private String message;
        private int status;
        private LocalDateTime time;

        public ErrorMessage(String message, int status) {
            this.message = message;
            this.status = status;
            this.time = LocalDateTime.now();
        }
    }
}

