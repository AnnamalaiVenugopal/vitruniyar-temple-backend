package com.vitruniyar.temple.entity.builder;

import com.vitruniyar.temple.dto.ArchanaTicketBookingDTO;
import com.vitruniyar.temple.entity.ArchanaTicket;
import com.vitruniyar.temple.entity.ArchanaTicketStatus;
import com.vitruniyar.temple.exception.ArchanaTicketBuilderException;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArchanaTicketBuilder {
    private static final Logger LOGGER = Logger.getLogger(ArchanaTicketBuilder.class.getName());
    public static ArchanaTicket buildArchanaTicket(ArchanaTicketBookingDTO archanaTicketBookingDTO){
        LOGGER.entering("ArchanaTicketBuilder", "buildArchanaTicket", archanaTicketBookingDTO);
        ArchanaTicket archanaTicket = null;
        try{
            archanaTicket = new ArchanaTicket();
            archanaTicket.setName(archanaTicketBookingDTO.getName());
            archanaTicket.setPhoneNumber(archanaTicketBookingDTO.getPhoneNumber());
            archanaTicket.setEmailId(archanaTicketBookingDTO.getEmailId());
            archanaTicket.setBookingDate(archanaTicketBookingDTO.getBookingDate());
            archanaTicket.setBookingTime(archanaTicketBookingDTO.getBookingTime());
            archanaTicket.setCreatedOn(LocalDateTime.now());
            archanaTicket.setArchanaTicketBookingStatus(ArchanaTicketStatus.BOOKED);
        }catch(Exception buildArchanaTicketException){
            LOGGER.log(Level.SEVERE, "Exception occurred while building the Archana Ticket from DTO ", buildArchanaTicketException);
            throw new ArchanaTicketBuilderException("Exception occurred while building the Archana Ticket from DTO ", buildArchanaTicketException);
        }
        LOGGER.exiting("ArchanaTicketBuilder", "buildArchanaTicket", archanaTicket);
        return archanaTicket;
    }
}
