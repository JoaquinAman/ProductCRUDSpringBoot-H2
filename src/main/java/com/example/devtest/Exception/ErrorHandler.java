package com.example.devtest.Exception;

import com.example.devtest.DTO.Response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
//import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(HttpServletRequest request,
                                                           EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(buildResponse(e, HttpStatus.NOT_FOUND));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(HttpServletRequest request, Exception e) {
        return ResponseEntity.internalServerError()
                .body(buildResponse(e, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(HttpServletRequest request, IOException e) {
        return ResponseEntity.badRequest()
                .body(buildResponse(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(HttpServletRequest request,
                                                                ConstraintViolationException e) {
        return ResponseEntity.badRequest()
                .body(buildResponse(e, HttpStatus.BAD_REQUEST));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumenNotValidException(HttpServletRequest request,
                                                                  MethodArgumentNotValidException e) {

        List<FieldError> errorFields = e.getFieldErrors();
        String errorMessage = "";
        for (FieldError fieldError : errorFields) {
            String field = fieldError.getField();
            errorMessage += fieldError.getDefaultMessage().replaceAll("%s", field) + ".";

            if (errorFields.indexOf(fieldError) != e.getErrorCount() - 1) {
                errorMessage += " ";
            }
        }

        return ResponseEntity.badRequest()
                .body(buildResponse(errorMessage, HttpStatus.BAD_REQUEST));
    }
    private ErrorResponse buildResponse(Exception e, HttpStatus httpStatus) {
        return new ErrorResponse(e, httpStatus.value());
    }

    private ErrorResponse buildResponse(String message, HttpStatus httpStatus) {
        return new ErrorResponse(message, httpStatus.value());
    }

}