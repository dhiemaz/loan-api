package com.java.app.loan.utils;

import com.java.app.loan.constants.Constant;
import lombok.NonNull;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtil {
    /***
     * getDateFromString
     * @param formatter
     * @param input
     * @return
     */
    public static Date getDateFromString(@NonNull DateTimeFormatter formatter, @NonNull String input) {
        //parse input string date by formatter to LocalDateTime
        final LocalDateTime localDateTime = LocalDateTime.parse(input, formatter);
        final Instant instLocalDateTime = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

        //Instant LocalDateTime convert to date
        return Date.from(instLocalDateTime);
    }

    /***
     * getStringFromDate
     * @param formatter
     * @param input
     * @return
     */
    public static String getStringFromDate(@NonNull DateTimeFormatter formatter, @NonNull Date input) {
        final LocalDateTime localDateTime = LocalDateTime.ofInstant(input.toInstant(), ZoneId.systemDefault());
        return localDateTime.format(formatter);
    }

    /***
     * getDueDate
     * @param startDate
     * @param duration
     * @return
     * @throws ParseException
     */
    public static Date getDateAfter(Date startDate, int duration) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(Arrays.asList(Constant.DATETIME_FORMAT).get(0));
        Date endDate = dateFormat.parse(dateFormat.format(new Date()));
        return DateUtils.addDays(endDate, duration);
    }

    /***
     * validateDateRange
     * @param startDate
     * @param endDate
     * @return boolean
     */
    private boolean validateDateRange(Date startDate, Date endDate) {
        LocalDateTime localStartDate = startDate.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime localEndDate = endDate.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();

        long start = localStartDate.toInstant(ZoneOffset.UTC).toEpochMilli();
        long end = localEndDate.toInstant(ZoneOffset.UTC).toEpochMilli();

        if ((end - start) > TimeUnit.DAYS.toMillis(90)) {
            return false;
        }

        return true;
    }
}
