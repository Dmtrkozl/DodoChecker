package com.dvobient.dodoChecker.Bot.Messages.Service;

import com.dvobient.dodoChecker.Bot.Messages.InlineKeyboard;
import com.dvobient.dodoChecker.Bot.Messages.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class EnableUpdateCheckMessage implements Message {
    public SendMessage sendMessage(long chatId) {
        InlineKeyboard inlineKeyboard = new InlineKeyboard();
        InlineKeyboard.updateCheck = true;
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText("Проверка обновлений включена ✅");
        sendMessage.setReplyMarkup(inlineKeyboard.getInlineMessage());
        return sendMessage;
    }
}
