package com.vitruniyar.temple.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ArchanaTicket {
    @Id
    @GeneratedValue
    @GenericGenerator(
            name = "sequence-generator",
            type = SequenceStyleGenerator.class,
            parameters = {
                    @Parameter(name = "sequence_name", value = "VIT-ARCH-"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "10")
            }
    )
    private long ticketId;
    private String name;
    private String phoneNumber;
    private String emailId;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private LocalDateTime createdOn;
    private ArchanaTicketStatus archanaTicketBookingStatus;
}
