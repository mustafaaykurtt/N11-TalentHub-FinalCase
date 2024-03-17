package com.n11.userservice.error;

import com.n11.userservice.exception.NotFoundException;
import com.n11.userservice.general.GeneralMessages;
import com.n11.userservice.general.GenericRestResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


/**
 * Created By Mustafa Aykurt
 * Date:26.02.2024
 * Time:16:51
 */

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
     ResponseEntity<GenericRestResponse<GenericErrorMessages>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                       HttpServletRequest request) {

        String message = GeneralMessages.getMessageForLocale("n11.error.validation", LocaleContextHolder.getLocale());
        var validationErrors = exception.getBindingResult().getFieldErrors()
                .stream().collect((Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
                        ((existing, replacing) -> existing))));

        var genericErrorMessages = new GenericErrorMessages();
        genericErrorMessages.setValidationErrors(validationErrors);
        genericErrorMessages.setPath(request.getRequestURI());

        var restResponse = GenericRestResponse.error(genericErrorMessages, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public final ResponseEntity<GenericRestResponse<GenericErrorMessages>> notFoundExceptionHandler(NotFoundException exception,
                                                                HttpServletRequest request) {
        String message = exception.getMessage();
        GenericErrorMessages genericErrorMessages = new GenericErrorMessages();
        genericErrorMessages.setPath(request.getRequestURI());

        var restResponse = GenericRestResponse.error(genericErrorMessages, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
    }
}
