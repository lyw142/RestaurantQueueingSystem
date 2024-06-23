package com.example.queueSystem.message;

import java.io.InputStream;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Component
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @Value("${phone.number}")
    private String PHONE_NUMBER;
    
    @Value("${twilio.sms.phone.number}")
    private String TWILIO_SMS_PHONE_NUMBER;

    @Value("${twilio.whatsapp.phone.number}")
    private String TWILIO_WHATSAPP_PHONE_NUMBER;

    public Map<String, String> buildTwilioAPI() {
        Map<String, String> loadDetails = new HashMap<>();

        Properties properties = new Properties();
        try (InputStream input = TwilioConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.err.println("Sorry, unable to find application.properties");
                System.exit(1);
            }

            properties.load(input);

            String ACCOUNT_SID = properties.getProperty("twilio.account.sid");
            String AUTH_TOKEN = properties.getProperty("twilio.auth.token");
            String PHONE_NUMBER = properties.getProperty("phone.number");
            String TWILIO_SMS_PHONE_NUMBER = properties.getProperty("twilio.sms.phone.number");
            String TWILIO_WHATSAPP_PHONE_NUMBER = properties.getProperty("twilio.whatsapp.phone.number");

            if (ACCOUNT_SID == null || AUTH_TOKEN == null || PHONE_NUMBER == null || TWILIO_SMS_PHONE_NUMBER == null || TWILIO_WHATSAPP_PHONE_NUMBER == null) {
                System.err.println(
                        "Please set the token, phone number and properties in the application.properties file.");
                System.exit(1);
            }

            loadDetails.put("ACCOUNT_SID", ACCOUNT_SID);
            loadDetails.put("AUTH_TOKEN", AUTH_TOKEN);
            loadDetails.put("PHONE_NUMBER", PHONE_NUMBER);
            loadDetails.put("TWILIO_SMS_PHONE_NUMBER", TWILIO_SMS_PHONE_NUMBER);
            loadDetails.put("TWILIO_WHATSAPP_PHONE_NUMBER", TWILIO_WHATSAPP_PHONE_NUMBER);

            return loadDetails;

        } catch (Exception ex) {
            ex.printStackTrace();

            return loadDetails;
        }
    }

    public boolean SendSMS(String restaurant, String queueNumber, String waitingTime, String phoneNum) {
        Map<String,String> TwilioDetails = buildTwilioAPI();

        StringBuilder messageContent = new StringBuilder();
        messageContent.append("\n Thank you for queueing at ").append(restaurant).append("\n")
                .append("Below are the details:\n")
                .append("Queue Number: ").append(queueNumber).append("\n")
                .append("Waiting Time: ").append(waitingTime).append(".\n")
                .append("Thank You.");

        Twilio.init(TwilioDetails.get("ACCOUNT_SID"), TwilioDetails.get("AUTH_TOKEN"));

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(TwilioDetails.get("PHONE_NUMBER")),
                new com.twilio.type.PhoneNumber(TwilioDetails.get("TWILIO_SMS_PHONE_NUMBER")),
                messageContent.toString())
                .create();

        System.out.println(message.getSid());

        if(!message.getSid().equals(null)) {
            return true;
        }

        return false;
    }

    public boolean SendWhatsappMessage(String restaurant, String queueNumber, String waitingTime, String phoneNum) {
        Map<String,String> TwilioDetails = buildTwilioAPI();

        StringBuilder messageContent = new StringBuilder();
        messageContent.append("\n Thank you for queueing at ").append(restaurant).append("\n")
                .append("Below are the details:\n")
                .append("Queue Number: ").append(queueNumber).append("\n")
                .append("Waiting Time: ").append(waitingTime).append(".\n")
                .append("Thank You.");

        Twilio.init(TwilioDetails.get("ACCOUNT_SID"), TwilioDetails.get("AUTH_TOKEN"));

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + TwilioDetails.get("PHONE_NUMBER")),
                new com.twilio.type.PhoneNumber("whatsapp:" + TwilioDetails.get("TWILIO_WHATSAPP_PHONE_NUMBER")),
                messageContent.toString())
                .create();

        System.out.println(message.getSid());

        if(!message.getSid().equals(null)) {
            return true;
        }

        return false;
    }
}
