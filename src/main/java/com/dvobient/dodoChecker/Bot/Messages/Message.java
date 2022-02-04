package com.dvobient.dodoChecker.Bot.Messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Message {
    SendMessage sendMessage(long chatId);
}
