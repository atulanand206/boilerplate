package com.creations.funds.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final double ONE_DAY_IN_MILLIS = 24.0 * 60.0 * 60.0 * 1000.0;

    private static Date getStartDate(Date lastDate, int period, int horizon) {
        final var c = Calendar.getInstance();
        c.setTime(lastDate);
        c.add(Calendar.YEAR, -1 * (period + horizon));
        return c.getTime();
    }
}
