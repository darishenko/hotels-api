package org.test.hotelsapi.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.test.hotelsapi.exception.dto.ExceptionResponse;
import org.test.hotelsapi.exception.hotel.DuplicateHotelException;
import org.test.hotelsapi.exception.hotel.HotelNotFoundException;

@RestControllerAdvice
public class HotelExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HotelNotFoundException.class)
    public ExceptionResponse handleHotelNotFoundException(HotelNotFoundException ex) {
        return new ExceptionResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateHotelException.class)
    public ExceptionResponse handleDuplicateHotelException(DuplicateHotelException ex) {
        return new ExceptionResponse(ex.getMessage());
    }
}
