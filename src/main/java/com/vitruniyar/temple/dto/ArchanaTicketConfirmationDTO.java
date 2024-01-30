package com.vitruniyar.temple.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArchanaTicketConfirmationDTO {
    private String ticketId;
    private String name;
    private String bookingStatus;
    private String bookingDateAndTime;
    private String bookedOn;

    public ArchanaTicketConfirmationDTO setTicketId(String ticketId){
        this.ticketId = ticketId;
        return this;
    }

    public ArchanaTicketConfirmationDTO setName(String name){
        this.name = name;
        return this;
    }

    public ArchanaTicketConfirmationDTO setBookingStatus(String bookingStatus){
        this.bookingStatus = bookingStatus;
        return this;
    }

    public ArchanaTicketConfirmationDTO setBookingDateAndTime(String bookingDateAndTime){
        this.bookingDateAndTime = bookingDateAndTime;
        return this;
    }

    public ArchanaTicketConfirmationDTO setBookedOn(String bookedOn){
        this.bookedOn = bookedOn;
        return this;
    }
}
