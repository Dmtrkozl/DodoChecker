package com.dvobient.dodoChecker.Bot.Messages.Error;

import com.dvobient.dodoChecker.Bot.Messages.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TokenErrorMessage implements Message {
    public SendMessage sendMessage(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText("❌ОШИБКА. Указан некорректный токен❌");
        return sendMessage;
    }
}
