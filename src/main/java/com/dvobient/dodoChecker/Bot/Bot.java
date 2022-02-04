package com.dvobient.dodoChecker.Bot;

import com.dvobient.dodoChecker.Bot.Messages.Data.ShowPositionsMessage;
import com.dvobient.dodoChecker.Bot.Messages.Data.UpdateMessage;
import com.dvobient.dodoChecker.Bot.Messages.Error.AccessErrorMessage;
import com.dvobient.dodoChecker.Bot.Messages.Service.DisableUpdateCheckMessage;
import com.dvobient.dodoChecker.Bot.Messages.Service.EnableUpdateCheckMessage;
import com.dvobient.dodoChecker.Bot.Messages.Service.StartMessage;
import com.dvobient.dodoChecker.Parser.HtmlParser;
import com.dvobient.dodoChecker.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.Set;

public class Bot extends TelegramLongPollingBot {
    private final Logger logger = LoggerFactory.getLogger(Bot.class);
    private final HtmlParser htmlParser = new HtmlParser(this);
    private static final Set<Long> chatIdSet = new HashSet<>();

    @Override
    public String getBotUsername() {
        return Utils.getBotName();
    }

    @Override
    public String getBotToken() {
        return Utils.getBotToken();
    }

    public void sendUpdates(String[] update) {
        for (String mes : update) {
            if (!mes.isEmpty()) {
                logger.info("new updates from Dodo received");
                UpdateMessage updateMessage = new UpdateMessage();
                for (long l : chatIdSet) {
                    try {
                        execute(updateMessage.sendMessage(l, mes));
                        logger.info("updates sent");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                logger.info("no updates to sent");
                return;
            }
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            logger.info("message updates from TG received:");
            logger.info("message: " + update.getMessage().getText());
            logger.info("userName: " + update.getMessage().getFrom().getUserName());
            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equalsIgnoreCase("/start")) {
                    try {
                        long chatId = update.getMessage().getChatId();
                        if (Utils.getUserNames().contains(update.getMessage().getFrom().getUserName())) {
                            chatIdSet.add(chatId);
                            execute(new StartMessage().sendMessage(chatId));
                            logger.info("start message send");
                        } else {
                            execute(new AccessErrorMessage().sendMessage(chatId));
                        }
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {
            logger.info("\ncallback update from TG received");
            logger.info("message: " + update.getCallbackQuery().getData());
            try {
                Message callbackMessage = update.getCallbackQuery().getMessage();
                long chatId = callbackMessage.getChatId();
                chatIdSet.add(chatId);

                String messageText = update.getCallbackQuery().getData();
                switch (messageText) {
                    case "checkup list":
                        String[] data = htmlParser.getData();
                        for (String mes : data) {
                            if (!mes.isEmpty()) {
                                execute(new ShowPositionsMessage().sendMessage(chatId, mes));
                                logger.info("Positions message sent to user");
                            } else execute(new ShowPositionsMessage().sendMessage(chatId, "Нет доступных позиций"));
                        }
                        break;
                    case "enable update check":
                        htmlParser.enableCheckerThread();
                        for (long l : chatIdSet) {
                            execute(new EnableUpdateCheckMessage().sendMessage(l));
                        }
                        break;
                    case "disable update check":
                        htmlParser.disableCheckerThread();
                        for (long l : chatIdSet) {
                            execute(new DisableUpdateCheckMessage().sendMessage(l));
                        }
                        break;
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
