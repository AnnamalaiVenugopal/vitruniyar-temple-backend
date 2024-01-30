package com.vitruniyar.temple.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArchanaTicketMessageDTO {
    private String recipientPhoneNumber;
    private String message;

    public ArchanaTicketMessageDTO setRecipientPhoneNumber(String recipientPhoneNumber){
        this.recipientPhoneNumber = recipientPhoneNumber;
        return this;
    }

    public ArchanaTicketMessageDTO setMessage(String message){
        this.message = message;
        return this;
    }
}
