package com.vitruniyar.temple.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ArchanaTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "archana_ticket_seq")
    @SequenceGenerator(name = "archana_ticket_seq", sequenceName = "archana_ticket_seq", initialValue = 1000, allocationSize = 1)
    private long ticketId;
    private String name;
    private String phoneNumber;
    private String emailId;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private LocalDateTime createdOn;
    private ArchanaTicketStatus archanaTicketBookingStatus;
}
