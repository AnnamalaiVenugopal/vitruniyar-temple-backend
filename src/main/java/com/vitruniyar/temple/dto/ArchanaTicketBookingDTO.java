package com.vitruniyar.temple.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ArchanaTicketBookingDTO {
    private String name;
    private String phoneNumber;
    private String emailId;
    private LocalDate bookingDate;
    private LocalTime bookingTime;

    @Override
    public String toString() {
        return "Name = "+ this.name+" Phone Number = "+this.phoneNumber+" Email Id = "+ this.emailId+" Booking Date = "+ this.bookingDate+" Booking Time = "+this.bookingTime;
    }
}
