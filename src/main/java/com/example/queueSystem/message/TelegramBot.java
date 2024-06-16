package com.example.queueSystem.message;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // Handle incoming updates (messages, commands, etc.)
        if (update.hasMessage()) {
            System.out.println(update.getMessage().getText());
        }
    }

    @Override
    public String getBotToken() {
        return "7014614494:AAGsyHOY8DJPZQP8MWN8I1mcwYYyKmXhbM4";
    }

    @Override
    public String getBotUsername() {
        return "restaurantqueuingsystem_bot";
    }

    public void sendMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(Long.toString(chatId), message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
