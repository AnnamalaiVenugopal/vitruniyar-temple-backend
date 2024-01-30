package com.vitruniyar.temple.exception;

import com.vitruniyar.temple.dto.ArchanaBookingExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ArchanaTicketExceptionHandler {

    @ExceptionHandler(value = ArchanaTicketBookingException.class)
    public ResponseEntity<ArchanaBookingExceptionDTO> handleArchanaTicketBookingException(ArchanaTicketBookingException archanaTicketBookingException){
        return ResponseEntity.internalServerError().body(new ArchanaBookingExceptionDTO("SEVERE", archanaTicketBookingException.getMessage()));
    }
}
