package com.example.queueSystem.message;

public class SMS {

    public static void main(String[] args) {
        String queueNumber = "70";
        String waitingTime = "20 minutes";

        TwilioConfig tc = new TwilioConfig();
        System.out.println(tc.buildTwilioAPI());

        System.out.println(tc.SendSMS("Din Tai Fung", queueNumber, waitingTime, "+6598804565"));
        System.out.println(tc.SendWhatsappMessage("Din Tai Fung", queueNumber, waitingTime, "+6598804565"));
    }
}
