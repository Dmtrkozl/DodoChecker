package com.dvobient.dodoChecker.Bot.Messages.Error;

import com.dvobient.dodoChecker.Bot.Messages.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class AccessErrorMessage implements Message {
    public SendMessage sendMessage(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText("❌ОШИБКА. У вас нет доступа❌");
        return sendMessage;
    }
}
