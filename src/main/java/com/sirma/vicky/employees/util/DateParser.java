package com.sirma.vicky.employees.util;

import com.sirma.vicky.employees.config.AppConfigProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Component
public class DateParser {

    private final List<DateTimeFormatter> formatters;

    public DateParser(@Qualifier("appConfigProperties") AppConfigProperties config) {
        this.formatters = config.getSupportedDateFormats().stream()
                .map(DateTimeFormatter::ofPattern)
                .toList();
    }

    public LocalDate parse(String input) {
        if (input == null || input.trim().equalsIgnoreCase("NULL")) {
            return LocalDate.now();
        }

        return formatters.stream()
                .map(formatter -> tryParse(input.trim(), formatter))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported date format: " + input));
    }

    private Optional<LocalDate> tryParse(String input, DateTimeFormatter formatter) {
        try {
            return Optional.of(LocalDate.parse(input, formatter));
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }
}
