package com.vitruniyar.temple.exception;

public class ArchanaTicketMessageBuilderException extends RuntimeException{
    public ArchanaTicketMessageBuilderException(String message){
        super(message);
    }

    public ArchanaTicketMessageBuilderException(String message, Throwable throwable){
        super(message, throwable);
    }
}
