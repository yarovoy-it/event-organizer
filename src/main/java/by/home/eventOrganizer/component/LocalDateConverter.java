package by.home.eventOrganizer.component;

import org.dozer.DozerConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Local date converter.
 */
public class LocalDateConverter extends DozerConverter<LocalDate, String> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Instantiates a new Local date converter.
     */
    public LocalDateConverter() {
        super(LocalDate.class, String.class);
    }

    @Override
    public String convertTo(LocalDate localDate, String s) {
        return localDate.format(formatter);
    }

    @Override
    public LocalDate convertFrom(String s, LocalDate localDate) {
        return LocalDate.parse(s, formatter);
    }
}

