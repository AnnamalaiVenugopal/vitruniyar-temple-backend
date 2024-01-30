package com.vitruniyar.temple.exception;

public class ArchanaTicketMessageException extends RuntimeException{
    public ArchanaTicketMessageException(String message){
        super(message);
    }

    public ArchanaTicketMessageException(String message, Throwable throwable){
        super(message, throwable);
    }
}
