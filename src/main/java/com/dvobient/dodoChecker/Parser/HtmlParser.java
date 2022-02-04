package com.dvobient.dodoChecker.Parser;

import com.dvobient.dodoChecker.Bot.Bot;
import com.dvobient.dodoChecker.Parser.Entity.Slot;
import com.dvobient.dodoChecker.Utils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlParser {
    private static final Logger logger = LoggerFactory.getLogger(HtmlParser.class);
    private final JsonProcessor jsonProcessor = new JsonProcessor();
    private final Bot bot;
    private final Model model = new Model();
    CheckerThread checkerThread = new CheckerThread(this);

    public HtmlParser(Bot bot) {
        this.bot = bot;
    }

    public Bot getBot() {
        return bot;
    }

    public void enableCheckerThread() {
        checkerThread.enableThread();
        logger.info("checker thread enable");
    }

    public void disableCheckerThread() {
        checkerThread.disableThread();
        logger.info("checker thread disable");
    }


    public String[] getData() {
        logger.info("getData method invoked");
        try {
            String jsonString = getJsonString(); //Получаем JSON
            logger.info("get json string: " + jsonString);
            List<Slot> slotList = jsonProcessor.parseJsonString(jsonString); //Получаем List<Slot>
            logger.info("get slotList: ");
            slotList.forEach(slot -> logger.info(slot.toString()));
            logger.info("add data in model");
            model.addData(slotList);
            String data = jsonProcessor.formatData(slotList); //Форматируем список в строку и возвращаем
            logger.info("get formatted data: " + data);
            return splitMessage(data);
        } catch (IOException e) {
            throw new RuntimeException("get data error");
        }
    }

    public String[] getUpdateData() {
        logger.info("getUpdate method invoked");
        try {
            String jsonString = getJsonString(); //Получаем JSON
            logger.info("get json string: " + jsonString);
            List<Slot> slotList = jsonProcessor.parseJsonString(jsonString); //Получаем List<Slot>
            logger.info("get slotList: ");
            slotList.forEach(slot -> logger.info(slot.toString()));
            model.addData(slotList); //Добавим данные процессора в модель
            logger.info("add data in model");
            List<Slot> updates = model.findDiff();  //найдем различия между до и после
            logger.info("find difference: ");
            updates.forEach(update -> logger.info(update.toString()));
            String data = jsonProcessor.formatData(updates); //Форматируем список в строку и возвращаем
            logger.info("get formatted data:\n" + data);
            return splitMessage(data);
        } catch (IOException e) {
            throw new RuntimeException("get update error");
        }
    }

    static String[] splitMessage(String str) {
        String[] res = new String[2];
        if (str.length() > 4096) {
            res[0] = str.substring(0, 4096);
            res[1] = str.substring(4096);
        } else return new String[]{str};
        return res;
    }

    private String getJsonString() throws IOException {
        logger.info("getJsonString invoked");
        Map<String, String> hm = new HashMap<>();
        hm.put("Host", "lk.dodocontrol.ru");
        hm.put("Accept", "application/json");
        hm.put("Content-Type", " application/json; charset=utf-8");
        hm.put("Referer", "https://lk.dodocontrol.ru/");

        Connection connection = Jsoup.connect("https://lk.dodocontrol.ru/api/personalarea/checkups/availableslots").
                cookie("token", Utils.getDodoToken()).
                headers(hm).
                ignoreContentType(true).
                userAgent("Mozilla");
        return connection.execute().body();
    }
}