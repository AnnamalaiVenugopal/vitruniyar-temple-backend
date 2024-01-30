package com.vitruniyar.temple.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArchanaTicketMessage {
    private String recipientPhoneNumber;
    private String message;
}
