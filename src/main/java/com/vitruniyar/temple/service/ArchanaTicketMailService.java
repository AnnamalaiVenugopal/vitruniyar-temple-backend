package com.vitruniyar.temple.service;

import com.vitruniyar.temple.entity.ArchanaTicketMail;
import com.vitruniyar.temple.exception.ArchanaTicketMailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ArchanaTicketMailService {

    private static final Logger LOGGER = Logger.getLogger(ArchanaTicketMailService.class.getName());
    @Autowired
    private JavaMailSender emailSender;

    public void sendTicketBookingMail(ArchanaTicketMail archanaTicketMail) throws MessagingException {
        LOGGER.entering("ArchanaTicketMailService", "sendTicketBookingMail", archanaTicketMail);
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(archanaTicketMail.getRecipientMailAddress());
            helper.setSubject(archanaTicketMail.getSubject());
            helper.setText(archanaTicketMail.getBody(), true);
            emailSender.send(message);
        }catch(Exception sendTicketBookingMailException){
            LOGGER.log(Level.SEVERE, "Exception occurred while sending ticket as email ", sendTicketBookingMailException);
            throw new ArchanaTicketMailException("Exception occurred while sending ticket as email ", sendTicketBookingMailException);
        }
        LOGGER.exiting("ArchanaTicketMailService", "sendTicketBookingMail");
    }
}
