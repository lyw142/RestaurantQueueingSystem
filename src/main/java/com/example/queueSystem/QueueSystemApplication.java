package com.example.queueSystem;

import com.example.queueSystem.message.TelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@CrossOrigin(origins = "http://localhost:3000")
@SpringBootApplication
public class QueueSystemApplication {

	public static void main(String[] args) {

		// try {
		// 	TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
		// 	telegramBotsApi.registerBot(new TelegramBot());
		// 	System.out.println("Bot Started Successfully");
		// } catch (TelegramApiException e) {
		// 	e.printStackTrace();
		// }

		SpringApplication.run(QueueSystemApplication.class, args);
	}

}
