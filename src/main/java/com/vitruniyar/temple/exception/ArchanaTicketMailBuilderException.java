package com.vitruniyar.temple.exception;

public class ArchanaTicketMailBuilderException extends RuntimeException{

    public ArchanaTicketMailBuilderException(String message){
        super(message);
    }

    public ArchanaTicketMailBuilderException(String message, Throwable throwable){
        super(message, throwable);
    }
}
