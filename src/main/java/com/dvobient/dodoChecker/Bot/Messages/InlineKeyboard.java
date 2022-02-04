package com.dvobient.dodoChecker.Bot.Messages;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboard {
    public static boolean updateCheck = false;

    public InlineKeyboardMarkup getInlineMessage() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("✅Обновить список✅");
        inlineKeyboardButton1.setCallbackData("checkup list");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        if (updateCheck) {
            inlineKeyboardButton2.setText("❌");
            inlineKeyboardButton2.setCallbackData("disable update check");

        } else {
            inlineKeyboardButton2.setText("\uD83D\uDD04");
            inlineKeyboardButton2.setCallbackData("enable update check");
        }
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
