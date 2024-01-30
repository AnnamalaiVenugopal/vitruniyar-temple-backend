package com.vitruniyar.temple.controller;

import com.vitruniyar.temple.dto.ArchanaTicketBookingDTO;
import com.vitruniyar.temple.dto.ArchanaTicketConfirmationDTO;
import com.vitruniyar.temple.service.ArchanaTicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("archanaTicketBooking")
@CrossOrigin(origins = "*")
public class ArchanaTicketBookingController {

    @Autowired
    ArchanaTicketBookingService archanaTicketBookingService;

    @PostMapping("bookTicket")
    public ArchanaTicketConfirmationDTO bookTicket(@RequestBody ArchanaTicketBookingDTO archanaTicketBookingDTO){
        return archanaTicketBookingService.bookArchanaTicket(archanaTicketBookingDTO);
    }
}
