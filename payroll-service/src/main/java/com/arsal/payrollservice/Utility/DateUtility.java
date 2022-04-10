package com.arsal.payrollservice.Utility;

import com.arsal.payrollservice.dto.PayPeriodDto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtility {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    private static final ZoneId defaultZoneId = ZoneId.systemDefault();

    public static Date getDate(String date) {
        return Date.from(LocalDate.parse(date, formatter)
                .atStartOfDay().atZone(defaultZoneId).toInstant());
    }

    public static PayPeriodDto getPayPeriod(Date date) {
        LocalDate localDate = LocalDate.parse(date.toString());
        LocalDate startDate = null;
        LocalDate endDate = null;
        if(localDate.getDayOfMonth() > 15) {
            startDate = localDate.withDayOfMonth(16);
            endDate = localDate.withDayOfMonth(localDate.lengthOfMonth());
        } else {
            startDate = localDate.withDayOfMonth(1);
            endDate = localDate.withDayOfMonth(15);
        }

        return new PayPeriodDto(getDate(startDate.format(formatter)),
                getDate(endDate.format(formatter)));
    }


    public static String getPayPeriod(Date date, String employeeId) {
        LocalDate localDate = LocalDate.parse(date.toString());
        LocalDate startDate = null;
        LocalDate endDate = null;
        if(localDate.getDayOfMonth() > 15) {
            startDate = localDate.withDayOfMonth(16);
            endDate = localDate.withDayOfMonth(localDate.lengthOfMonth());
        } else {
            startDate = localDate.withDayOfMonth(1);
            endDate = localDate.withDayOfMonth(15);
        }
        return employeeId +"-"+ startDate.format(formatter) +"-" + endDate.format(formatter);
    }
}
