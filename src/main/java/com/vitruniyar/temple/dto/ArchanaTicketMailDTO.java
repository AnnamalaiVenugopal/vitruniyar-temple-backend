package com.vitruniyar.temple.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArchanaTicketMailDTO {
    private String recipientMailAddress;
    private String subject;
    private String body;

    public ArchanaTicketMailDTO setRecipientMailAddress(String recipientMailAddress){
        this.recipientMailAddress = recipientMailAddress;
        return this;
    }

    public ArchanaTicketMailDTO setSubject(String subject){
        this.subject = subject;
        return this;
    }

    public ArchanaTicketMailDTO setBody(String body){
        this.body = body;
        return this;
    }
}
