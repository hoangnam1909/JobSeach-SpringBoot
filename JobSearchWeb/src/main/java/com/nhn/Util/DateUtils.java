package com.nhn.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DateUtils {

    public static Date getDateByMillisecond(String dateStr) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        return (Date) formatter.parse(dateStr);
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Set<Date> distinctDate(List<Date> dateList) throws ParseException {
        Set<Date> setDateList = new HashSet<>(dateList);
        Set<Date> dateListResult = new HashSet<>(dateList);

        for (Date date : setDateList) {
            dateListResult.add(DateUtils.removeTime(getDateByMillisecond(date.toString())));
        }

        return dateListResult;
    }

}
