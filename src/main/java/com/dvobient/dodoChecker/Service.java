package com.dvobient.dodoChecker;

import com.dvobient.dodoChecker.Bot.Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Service {
    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    public static void main(String[] args) {
        startService();
    }

    private static void startService() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot());
            logger.info("Bot started");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
