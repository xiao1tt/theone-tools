package com.theone.common.base.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author chenxiaotong
 */
public class DateFormatter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(LocalDateTime time) {
        return DATE_TIME_FORMATTER.format(time);
    }

    public static String format(LocalDate time) {
        return DATE_FORMATTER.format(time);
    }

    public static String format(LocalTime time) {
        return TIME_FORMATTER.format(time);
    }

    public static LocalDate parseDate(String text) {
        return LocalDate.parse(text, DATE_FORMATTER);
    }

    public static LocalDate parseDateTime(String text) {
        return LocalDate.parse(text, DATE_TIME_FORMATTER);
    }

    public static LocalDate parseTime(String text) {
        return LocalDate.parse(text, TIME_FORMATTER);
    }
}
