package com.vitruniyar.temple.exception;

public class ArchanaTicketBuilderException extends RuntimeException{
    public ArchanaTicketBuilderException(String message){
        super(message);
    }

    public ArchanaTicketBuilderException(String message, Throwable throwable){
        super(message, throwable);
    }
}
