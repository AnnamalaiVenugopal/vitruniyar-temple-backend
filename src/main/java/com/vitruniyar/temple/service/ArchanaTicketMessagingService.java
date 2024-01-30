package com.vitruniyar.temple.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.vitruniyar.temple.entity.ArchanaTicketMessage;
import com.vitruniyar.temple.exception.ArchanaTicketMessageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ArchanaTicketMessagingService {

    private static final Logger LOGGER = Logger.getLogger(ArchanaTicketMessagingService.class.getName());
    @Value("${twilio.account.sid}")
    private String accountSID ;
    @Value("${twilio.account.auth.id}")
    private String authID;
    @Value("${twilio.account.sender.phone.number}")
    private String senderPhoneNumber;


    public void sendTicketBookingMessage(ArchanaTicketMessage archanaTicketMessage){
        LOGGER.entering("ArchanaTicketMessagingService", "sendTicketBookingMessage", archanaTicketMessage);
        try{
            Twilio.init(accountSID, authID);
            Message.creator(new PhoneNumber(archanaTicketMessage.getRecipientPhoneNumber()), new PhoneNumber(senderPhoneNumber), archanaTicketMessage.getMessage())
                .create();
        }catch(Exception sendTicketBookingMessageException){
            LOGGER.log(Level.SEVERE, "Exception occurred while sending the ticket booking message ", sendTicketBookingMessageException);
            throw new ArchanaTicketMessageException("Exception occurred while sending the ticket booking message ", sendTicketBookingMessageException);
        }
        LOGGER.exiting("ArchanaTicketMessagingService", "sendTicketBookingMessage");
    }
}
