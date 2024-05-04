package com.openclassrooms.mddapi.mapper;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateMapper {

    public String asString(LocalDateTime date) {
        return date != null ? date.format(DateTimeFormatter.ISO_DATE_TIME) : null;
    }

    public LocalDateTime asDate(String date) {
        try {
            return date != null ? LocalDateTime.parse(date) : null;
        }
        catch ( DateTimeParseException e ) {
            throw new RuntimeException( e );
        }
    }

}
