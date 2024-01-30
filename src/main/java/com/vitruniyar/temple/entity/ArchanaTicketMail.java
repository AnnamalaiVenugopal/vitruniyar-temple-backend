package com.vitruniyar.temple.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArchanaTicketMail {
    private String recipientMailAddress;
    private String subject;
    private String body;
}
