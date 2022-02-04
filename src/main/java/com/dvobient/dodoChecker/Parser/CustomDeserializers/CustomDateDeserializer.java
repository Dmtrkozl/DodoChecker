package com.dvobient.dodoChecker.Parser.CustomDeserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*Иногда API отдает время в виде T00:00:00Z, вместо T00:00:00. Данный десериалайзер используется для корректных
* преобразований JSON String в Java object */

public class CustomDateDeserializer extends StdDeserializer<Date> {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss['Z']");

    public CustomDateDeserializer() {
        this(null);
    }

    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateString = p.getText();
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, dateTimeFormatter);
            LocalDate localDate = localDateTime.toLocalDate();
            return new SimpleDateFormat("yyyy-MM-dd").parse(localDate.toString());
        } catch (ParseException e) {
            throw new RuntimeException("Unable to parse date", e);
        }
    }
}
