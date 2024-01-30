package com.vitruniyar.temple.entity.builder;

import com.vitruniyar.temple.dto.ArchanaTicketMessageDTO;
import com.vitruniyar.temple.entity.ArchanaTicketMessage;
import com.vitruniyar.temple.exception.ArchanaTicketMessageBuilderException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ArchanaTicketMessageBuilder {
    private static final Logger LOGGER = Logger.getLogger(ArchanaTicketMessageBuilder.class.getName());
    public static ArchanaTicketMessage buildArchanaTicketMessage(ArchanaTicketMessageDTO archanaTicketMessageDTO){
        LOGGER.entering("ArchanaTicketMessageBuilder", "buildArchanaTicketMessage", archanaTicketMessageDTO);
        ArchanaTicketMessage archanaTicketMessage = null;
        try{
            archanaTicketMessage = new ArchanaTicketMessage(archanaTicketMessageDTO.getRecipientPhoneNumber(), archanaTicketMessageDTO.getMessage());
        }catch(Exception buildArchanaTicketMessageException){
            LOGGER.log(Level.SEVERE, "Exception occurred while building the archana ticket message ", buildArchanaTicketMessageException);
            throw new ArchanaTicketMessageBuilderException("Exception occurred while building the archana ticket message ", buildArchanaTicketMessageException);
        }
        LOGGER.exiting("ArchanaTicketMessageBuilder", "buildArchanaTicketMessage", archanaTicketMessage);
        return archanaTicketMessage;
    }
}
