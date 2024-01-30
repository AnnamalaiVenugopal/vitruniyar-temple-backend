package com.vitruniyar.temple.exception;

public class ArchanaTicketMailException extends RuntimeException{
    public ArchanaTicketMailException(String message){
        super(message);
    }

    public ArchanaTicketMailException(String message, Throwable throwable){
        super(message, throwable);
    }
}
