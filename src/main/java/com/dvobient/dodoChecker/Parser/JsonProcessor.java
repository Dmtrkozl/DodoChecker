package com.dvobient.dodoChecker.Parser;

import com.dvobient.dodoChecker.Parser.CustomDeserializers.CustomDateDeserializer;
import com.dvobient.dodoChecker.Parser.Entity.CheckType;
import com.dvobient.dodoChecker.Parser.Entity.CheckupData;
import com.dvobient.dodoChecker.Parser.Entity.Slot;
import com.dvobient.dodoChecker.Parser.Entity.Unit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.TextStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonProcessor {
    private static final Logger logger = LoggerFactory.getLogger(JsonProcessor.class);

    public List<Slot> parseJsonString(String jsonString) throws IOException {
        List<Slot> slotList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule dateModule = new SimpleModule();
        dateModule.addDeserializer(Date.class, new CustomDateDeserializer());
        mapper.registerModule(dateModule);

        CheckupData data = mapper.readValue(jsonString, CheckupData.class);
        for (Unit unit : data.getUnits()) {
            for (CheckType checkType : unit.getCheckTypes()) {
                slotList.addAll(checkType.getSlots());
            }
        }
        return slotList;
    }

    public String formatData(List<Slot> slotList) {
        TextStringBuilder tsb = new TextStringBuilder();
        if (slotList.size() == 0) {
            logger.info("no update found");
            return "";
        }
        Set<String> unitNames = new HashSet<>();
        int num = 0;
        for (Slot slot : slotList) {
            String unitName = slot.getUnitName();
            if (!unitNames.contains(unitName)) {
                num = 0;
                unitNames.add(unitName);
                tsb.appendln("Адрес: " + unitName + "🏢");
            }
            num++;
            tsb.append(String.valueOf(num)).append(". ");
            if (slot.getCheckType() == 0)
                tsb.append("Доставка ").appendln("\uD83D\uDE97");
            if (slot.getCheckType() == 1)
                tsb.append("Ресторан ").appendln("\uD83C\uDF7D");
            if (slot.getCheckType() == 2)
                tsb.append("Инспекция ").appendln("\uD83D\uDD75");
            tsb.append("Дата: ").appendln(new SimpleDateFormat("dd.MM.yyyy").format(slot.getDate()));
            tsb.appendln("Необходимо заказать: ");
            for (String product : slot.getProducts()) {
                if (StringUtils.containsIgnoreCase(product, "пицца")) tsb.append("\uD83C\uDF55");
                if (StringUtils.containsIgnoreCase(product, "картофель"))  tsb.append("\uD83C\uDF5F");
                if (StringUtils.containsIgnoreCase(product, "рулетик"))  tsb.append("\uD83E\uDD67");
                if (StringUtils.containsIgnoreCase(product, "додстер"))  tsb.append("\uD83C\uDF2F");
                if (StringUtils.containsIgnoreCase(product, "Стартер"))  tsb.append("\uD83E\uDD6A");
                if (StringUtils.containsIgnoreCase(product, "кофе"))  tsb.append("☕");
                tsb.append(product).append(System.getProperty("line.separator"));
            }
            tsb.appendNewLine();
        }
        return tsb.toString();
    }
}
