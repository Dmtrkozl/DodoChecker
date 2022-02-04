package com.dvobient.dodoChecker.Bot.Messages.Service;

import com.dvobient.dodoChecker.Bot.Messages.InlineKeyboard;
import com.dvobient.dodoChecker.Bot.Messages.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class DisableUpdateCheckMessage implements Message {
    public SendMessage sendMessage(long chatId) {
        InlineKeyboard inlineKeyboard = new InlineKeyboard();
        InlineKeyboard.updateCheck = false;
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText("Проверка обновлений выключена ❌");
        sendMessage.setReplyMarkup(inlineKeyboard.getInlineMessage());
        return sendMessage;
    }
}
