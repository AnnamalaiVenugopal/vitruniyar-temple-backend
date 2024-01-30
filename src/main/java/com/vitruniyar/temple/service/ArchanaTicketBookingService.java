package com.vitruniyar.temple.service;

import com.vitruniyar.temple.dto.ArchanaTicketBookingDTO;
import com.vitruniyar.temple.dto.ArchanaTicketConfirmationDTO;
import com.vitruniyar.temple.dto.ArchanaTicketMailDTO;
import com.vitruniyar.temple.dto.ArchanaTicketMessageDTO;
import com.vitruniyar.temple.entity.ArchanaTicket;
import com.vitruniyar.temple.entity.builder.ArchanaTicketBuilder;
import com.vitruniyar.temple.entity.builder.ArchanaTicketMailBuilder;
import com.vitruniyar.temple.entity.builder.ArchanaTicketMessageBuilder;
import com.vitruniyar.temple.exception.ArchanaTicketBookingException;
import com.vitruniyar.temple.repository.ArchanaTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ArchanaTicketBookingService {

    private static final Logger LOGGER = Logger.getLogger(ArchanaTicketBookingService.class.getName());
    @Autowired
    ArchanaTicketRepository archanaTicketRepository;

    @Autowired
    ArchanaTicketMailService archanaTicketMailService;

    @Autowired
    ArchanaTicketMessagingService archanaTicketMessagingService;

    public ArchanaTicketConfirmationDTO bookArchanaTicket(ArchanaTicketBookingDTO archanaTicketBookingDTO){
        LOGGER.entering("ArchanaTicketBookingService", "bookArchanaTicket");
        ArchanaTicket archanaTicket = ArchanaTicketBuilder.buildArchanaTicket(archanaTicketBookingDTO);
        ArchanaTicket responseArchanaTicket = archanaTicketRepository.save(archanaTicket);
        try{
            archanaTicketMailService.sendTicketBookingMail(ArchanaTicketMailBuilder.buildArchanaTicketMail(getArchanaTicketMailDTO(responseArchanaTicket)));
            archanaTicketMessagingService.sendTicketBookingMessage(ArchanaTicketMessageBuilder.buildArchanaTicketMessage(getArchanaTicketMessageDTO(responseArchanaTicket)));
        }catch(Exception bookArchanaTicketException){
            LOGGER.log(Level.SEVERE, "Exception occurred while booking the ticket ", bookArchanaTicketException);
        }
        LOGGER.exiting("ArchanaTicketBookingService","bookArchanaTicket");
        return getArchanaTicketConfirmationDTO(responseArchanaTicket);
    }

    private ArchanaTicketMailDTO getArchanaTicketMailDTO(ArchanaTicket archanaTicket){
        LOGGER.entering("ArchanaTicketBookingService", "getArchanaTicketMailDTO", archanaTicket);
        ArchanaTicketMailDTO archanaTicketMailDTO = null;
        try {
            archanaTicketMailDTO = new ArchanaTicketMailDTO().setRecipientMailAddress(archanaTicket.getEmailId())
                    .setSubject(getMailSubject(archanaTicket))
                    .setBody(getMailBody(archanaTicket));
        }catch (Exception getArchanaTicketMailDTOException){
            LOGGER.log(Level.SEVERE, "Exception occurred while getting the archana ticket mail DTO ", getArchanaTicketMailDTOException);
            throw new ArchanaTicketBookingException("Exception occurred while getting the archana ticket mail DTO ", getArchanaTicketMailDTOException);
        }
        LOGGER.exiting("ArchanaTicketBookingService", "getArchanaTicketMailDTO", archanaTicketMailDTO);
        return  archanaTicketMailDTO;
    }

    private String getMailSubject(ArchanaTicket archanaTicket){
        LOGGER.entering("ArchanaTicketBookingService", "getMailSubject", archanaTicket);
        String mailSubject = "";
        try{
            mailSubject = "Ticket booked with Reference Id "+ archanaTicket.getTicketId();
        }catch (Exception getMailSubjectException){
            LOGGER.log(Level.SEVERE, "Exception occurred while getting the mail subject ", getMailSubjectException);
            throw new ArchanaTicketBookingException("Exception occurred while getting the mail subject ", getMailSubjectException);
        }
        LOGGER.exiting("ArchanaTicketBookingService", "getMailSubject", mailSubject);
        return  mailSubject;
    }

    private String getMailBody(ArchanaTicket archanaTicket){
        LOGGER.entering("ArchanaTicketBookingService", "getMailBody", archanaTicket);
        StringBuilder mailBodyBuilder = new StringBuilder();
        try {
            mailBodyBuilder.append("<html>");
            mailBodyBuilder.append("<head>");
            mailBodyBuilder.append("</head>");
            mailBodyBuilder.append("<body>");
            mailBodyBuilder.append("<h3>Ticket Details : </h3>");
            mailBodyBuilder.append("<hr>");
            mailBodyBuilder.append("<table>");
            mailBodyBuilder.append("<tbody>");
            mailBodyBuilder.append("<tr>");
            mailBodyBuilder.append("<td> Ticket Id : </td>");
            mailBodyBuilder.append("<td>");
            mailBodyBuilder.append("VIT-ARCH-"+archanaTicket.getTicketId());
            mailBodyBuilder.append("</td>");
            mailBodyBuilder.append("</tr>");
            mailBodyBuilder.append("<td> Ticket Booked By : </td>");
            mailBodyBuilder.append("<td>");
            mailBodyBuilder.append(archanaTicket.getName());
            mailBodyBuilder.append("</td>");
            mailBodyBuilder.append("<tr>");
            mailBodyBuilder.append("<td> Ticket Booked contact number : </td>");
            mailBodyBuilder.append("<td>");
            mailBodyBuilder.append(archanaTicket.getPhoneNumber());
            mailBodyBuilder.append("</td>");
            mailBodyBuilder.append("</tr>");
            mailBodyBuilder.append("<tr>");
            mailBodyBuilder.append("<td> Ticket Booking Date and Time : </td>");
            mailBodyBuilder.append("<td>");
            mailBodyBuilder.append(archanaTicket.getBookingDate().format(DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy", Locale.ROOT)).toString() + " " + archanaTicket.getBookingTime().format(DateTimeFormatter.ofPattern("hh:mm a", Locale.ROOT)).toString());
            mailBodyBuilder.append("</td>");
            mailBodyBuilder.append("</tr>");
            mailBodyBuilder.append("<tr>");
            mailBodyBuilder.append("<td> Ticket Booking Status : </td>");
            mailBodyBuilder.append("<td>");
            mailBodyBuilder.append(archanaTicket.getArchanaTicketBookingStatus().toString());
            mailBodyBuilder.append("</td>");
            mailBodyBuilder.append("</tr>");
            mailBodyBuilder.append("</tbody>");
            mailBodyBuilder.append("</table>");
            mailBodyBuilder.append("</body>");
            mailBodyBuilder.append("</html>");
        }catch(Exception getMailBodyException){
            LOGGER.log(Level.SEVERE, "Exception occurred while building the mail body", getMailBodyException);
            throw new ArchanaTicketBookingException("Exception occurred while building the mail body", getMailBodyException);
        }
        LOGGER.exiting("ArchanaTicketBookingService", "getMailBody", mailBodyBuilder);
        return mailBodyBuilder.toString();
    }

    private ArchanaTicketMessageDTO getArchanaTicketMessageDTO(ArchanaTicket archanaTicket){
        LOGGER.entering("ArchanaTicketBookingService", "getArchanaTicketMessageDTO", archanaTicket);
        ArchanaTicketMessageDTO archanaTicketMessageDTO = null;
        try {
            archanaTicketMessageDTO = new ArchanaTicketMessageDTO().setRecipientPhoneNumber(archanaTicket.getPhoneNumber())
                    .setMessage(getMessage(archanaTicket));
        }catch(Exception getArchanaTicketMessageDTOException){
            LOGGER.log(Level.SEVERE, "Exception occurred while getting the Archana Ticket Message DTO ", getArchanaTicketMessageDTOException);
            throw new ArchanaTicketBookingException("Exception occurred while getting the Archana Ticket Message DTO ", getArchanaTicketMessageDTOException);
        }
        LOGGER.exiting("ArchanaTicketBookingService", "getArchanaTicketMessageDTO", archanaTicketMessageDTO);
        return archanaTicketMessageDTO;
    }
    private String getMessage(ArchanaTicket archanaTicket){
        LOGGER.entering("ArchanaTicketBookingService", "getMessage", archanaTicket);
        StringBuilder messageBuilder = new StringBuilder();
        try {
            messageBuilder.append("\n");
            messageBuilder.append("Ticket Details:\n\n");
            messageBuilder.append("--------------------\n");
            messageBuilder.append(" Ticket Id : ");
            messageBuilder.append("VIT-ARCH-"+archanaTicket.getTicketId());
            messageBuilder.append("\n");
            messageBuilder.append(" Ticket Booked By : ");
            messageBuilder.append(archanaTicket.getName());
            messageBuilder.append("\n");
            messageBuilder.append(" Ticket Booked contact number : ");
            messageBuilder.append(archanaTicket.getPhoneNumber());
            messageBuilder.append("\n");
            messageBuilder.append(" Ticket Booking Date and Time : ");
            messageBuilder.append(archanaTicket.getBookingDate().format(DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy", Locale.ROOT)).toString() + " " + archanaTicket.getBookingTime().format(DateTimeFormatter.ofPattern("hh:mm a", Locale.ROOT)).toString());
            messageBuilder.append("\n");
            messageBuilder.append(" Ticket Booking Status : ");
            messageBuilder.append(archanaTicket.getArchanaTicketBookingStatus().toString());
            messageBuilder.append("\n");
        }catch(Exception getMessageException){
            LOGGER.log(Level.SEVERE, "Exception occurred while building message for SMS ", getMessageException);
            throw new ArchanaTicketBookingException("Exception occurred while building message for SMS ", getMessageException);
        }
        LOGGER.exiting("ArchanaTicketBookingService", "getMessage",messageBuilder);
        return messageBuilder.toString();
    }

    private ArchanaTicketConfirmationDTO getArchanaTicketConfirmationDTO(ArchanaTicket archanaTicket){
        LOGGER.entering("ArchanaTicketBookingService", "getArchanaTicketConfirmationDTO", archanaTicket);
        ArchanaTicketConfirmationDTO archanaTicketConfirmationDTO = null;
        try {
            archanaTicketConfirmationDTO = new ArchanaTicketConfirmationDTO().setTicketId("VIT-ARCH-"+archanaTicket.getTicketId()).
                    setName(archanaTicket.getName()).
                    setBookingDateAndTime(archanaTicket.getBookingDate().format(DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy", Locale.ROOT)).toString() + " " + archanaTicket.getBookingTime().format(DateTimeFormatter.ofPattern("hh:mm a", Locale.ROOT)).toString()).
                    setBookedOn(archanaTicket.getCreatedOn().toString()).
                    setBookingStatus(archanaTicket.getArchanaTicketBookingStatus().toString());
        }catch(Exception getArchanaTicketConfirmationDTOException){
            LOGGER.log(Level.SEVERE, "Exception occurred while getting teh Archana Ticket Confirmation DTO ", getArchanaTicketConfirmationDTOException);
            throw new ArchanaTicketBookingException("Exception occurred while getting teh Archana Ticket Confirmation DTO ", getArchanaTicketConfirmationDTOException);
        }
        LOGGER.exiting("ArchanaTicketBookingService", "getArchanaTicketConfirmationDTO", archanaTicketConfirmationDTO);
        return archanaTicketConfirmationDTO;
    }
}
