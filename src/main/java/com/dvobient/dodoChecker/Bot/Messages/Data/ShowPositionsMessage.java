package com.dvobient.dodoChecker.Bot.Messages.Data;

import com.dvobient.dodoChecker.Bot.Messages.Error.TokenErrorMessage;
import com.dvobient.dodoChecker.Bot.Messages.InlineKeyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ShowPositionsMessage implements DataMessage {

    public SendMessage sendMessage(long chatId, String dodoData) {
        if(dodoData.equals("Token incorrect")) return new TokenErrorMessage().sendMessage(chatId);
        else {
            InlineKeyboard inlineKeyboard = new InlineKeyboard();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(Long.toString(chatId));
            sendMessage.setText(dodoData);
            sendMessage.setReplyMarkup(inlineKeyboard.getInlineMessage());
            return sendMessage;
        }
    }
}

