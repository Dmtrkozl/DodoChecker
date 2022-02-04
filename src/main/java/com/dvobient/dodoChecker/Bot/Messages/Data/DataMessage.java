package com.dvobient.dodoChecker.Bot.Messages.Data;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface DataMessage {
    SendMessage sendMessage(long chatId, String dodoData);
}
