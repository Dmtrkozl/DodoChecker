package com.dvobient.dodoChecker.Bot.Messages.Service;

import com.dvobient.dodoChecker.Bot.Messages.InlineKeyboard;
import com.dvobient.dodoChecker.Bot.Messages.Message;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartMessage implements Message {

    public SendMessage sendMessage(long chatId) {
        InlineKeyboard inlineKeyboard = new InlineKeyboard();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText("Приветствую! \uD83D\uDC35 Данный бот позволяет получить список доступных проверок, а также " +
                "сообщить о появлении новых проверок в ресторане Додо пицца. \uD83C\uDF55 \n\n" +
                "Для активации проверки обновлений нажмите - \uD83D\uDD04 \n" +
                "Для отключения проверки - ❌");
        sendMessage.setReplyMarkup(inlineKeyboard.getInlineMessage());
        return sendMessage;
    }
}