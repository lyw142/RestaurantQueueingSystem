package com.example.queueSystem.message;

import java.io.InputStream;
import java.util.Properties;

public class SMSTestData {

    public static void main(String[] args) {
        String queueNumber = "70";
        String waitingTime = "20 minutes";

        Properties prop = new Properties();
        try (InputStream input = SMSTestData.class.getClassLoader().getResourceAsStream("application.properties")) {
            prop.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String phoneNumber = prop.getProperty("phone.number");
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number is not set in the configuration file");
        }

        TwilioConfig tc = new TwilioConfig();
        System.out.println(tc.buildTwilioAPI());

        System.out.println(tc.SendSMS("Din Tai Fung", queueNumber, waitingTime, phoneNumber));
        System.out.println(tc.SendWhatsappMessage("Din Tai Fung", queueNumber, waitingTime, phoneNumber));
    }
}
