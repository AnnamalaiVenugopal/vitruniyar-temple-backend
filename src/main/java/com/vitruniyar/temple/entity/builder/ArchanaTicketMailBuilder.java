package com.vitruniyar.temple.entity.builder;

import com.vitruniyar.temple.dto.ArchanaTicketMailDTO;
import com.vitruniyar.temple.entity.ArchanaTicketMail;
import com.vitruniyar.temple.exception.ArchanaTicketMailBuilderException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ArchanaTicketMailBuilder {
    private static final Logger LOGGER = Logger.getLogger(ArchanaTicketMailBuilder.class.getName());
    public static ArchanaTicketMail buildArchanaTicketMail(ArchanaTicketMailDTO archanaTicketMailDTO){
        LOGGER.entering("ArchanaTicketMailBuilder", "buildArchanaTicketMail", archanaTicketMailDTO);
        ArchanaTicketMail archanaTicketMail = null;
        try{
            archanaTicketMail = new ArchanaTicketMail(archanaTicketMailDTO.getRecipientMailAddress(), archanaTicketMailDTO.getSubject(), archanaTicketMailDTO.getBody());
        }catch(Exception buildArchanaTicketMailException){
            LOGGER.log(Level.SEVERE, "Exception occurred while building the archana ticket mail ", buildArchanaTicketMailException);
            throw new ArchanaTicketMailBuilderException("Exception occurred while building the archana ticket mail ", buildArchanaTicketMailException);
        }
        LOGGER.exiting("ArchanaTicketMailBuilder", "buildArchanaTicketMail", archanaTicketMail);
        return archanaTicketMail;
    }
}
