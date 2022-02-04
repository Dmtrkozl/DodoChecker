package com.dvobient.dodoChecker.Bot.Messages.Data;

import com.dvobient.dodoChecker.Bot.Messages.InlineKeyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class UpdateMessage implements DataMessage {
    public SendMessage sendMessage(long chatId, String updateData) {
        InlineKeyboard inlineKeyboard = new InlineKeyboard();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText("Новые позиции: \uD83D\uDC7E\uD83D\uDC7E \n" + updateData);
        sendMessage.setReplyMarkup(inlineKeyboard.getInlineMessage());
        return sendMessage;
    }
}
