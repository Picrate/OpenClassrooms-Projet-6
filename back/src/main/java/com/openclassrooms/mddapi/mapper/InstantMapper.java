package com.openclassrooms.mddapi.mapper;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.format.DateTimeParseException;

@Component
public class InstantMapper {

    public String asString(Instant instant) {
        return instant != null ? instant.toString() : null;
    }

    public Instant asDate(String instant) {
        try {
            return instant != null ? Instant.parse(instant) : null;
        }
        catch ( DateTimeParseException e ) {
            throw new RuntimeException( e );
        }
    }
}
