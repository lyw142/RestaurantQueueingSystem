package com.example.queueSystem.message;

import java.io.InputStream;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Component
public class TwilioConfig {

    @Value("${TWILIO_ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${TWILIO_AUTH_TOKEN}")
    private String AUTH_TOKEN;

    public Map<String, String> buildTwilioAPI() {
        Map<String, String> loadDetails = new HashMap<>();

        Properties properties = new Properties();
        try (InputStream input = TwilioConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.err.println("Sorry, unable to find application.properties");
                System.exit(1);
            }

            properties.load(input);

            String ACCOUNT_SID = properties.getProperty("TWILIO_ACCOUNT_SID");
            String AUTH_TOKEN = properties.getProperty("TWILIO_AUTH_TOKEN");

            if (ACCOUNT_SID == null || AUTH_TOKEN == null) {
                System.err.println(
                        "Please set the TWILIO_ACCOUNT_SID and TWILIO_AUTH_TOKEN properties in the application.properties file.");
                System.exit(1);
            }

            loadDetails.put("ACCOUNT_SID", ACCOUNT_SID);
            loadDetails.put("AUTH_TOKEN", AUTH_TOKEN);

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
                new com.twilio.type.PhoneNumber(phoneNum),
                new com.twilio.type.PhoneNumber("+15306185927"),
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
                new com.twilio.type.PhoneNumber("whatsapp:" + phoneNum),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                messageContent.toString())
                .create();

        System.out.println(message.getSid());

        if(!message.getSid().equals(null)) {
            return true;
        }

        return false;
    }
}
