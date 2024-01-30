package com.vitruniyar.temple.exception;

public class ArchanaTicketBookingException extends RuntimeException{
    public ArchanaTicketBookingException(String message){
        super(message);
    }

    public ArchanaTicketBookingException(String message, Throwable throwable){
        super(message, throwable);
    }
}
