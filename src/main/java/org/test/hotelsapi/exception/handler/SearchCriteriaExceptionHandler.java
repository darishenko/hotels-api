package org.test.hotelsapi.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.test.hotelsapi.exception.InvalidSearchCriteriaException;
import org.test.hotelsapi.exception.dto.ExceptionResponse;

@RestControllerAdvice
public class SearchCriteriaExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidSearchCriteriaException.class)
    public ExceptionResponse handleInvalidSearchCriteriaException(InvalidSearchCriteriaException ex) {
        return new ExceptionResponse(ex.getMessage());
    }
}
