package com.dvobient.dodoChecker;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Utils {
    private static final Properties properties = new Properties();

    static {
        try {
            InputStream is = Utils.class.getResourceAsStream("/settings.properties");
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getUserNames() {
        return Arrays.asList(properties.getProperty("tg_user_names").split(","));
    }

    public static String getBotName() {
        return properties.getProperty("bot_name");
    }

    public static String getBotToken() {
        return properties.getProperty("bot_token");
    }

    public static String getDodoToken() {
        return properties.getProperty("dodo_token");
    }
}
