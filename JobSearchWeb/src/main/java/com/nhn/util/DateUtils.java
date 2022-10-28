package com.nhn.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static Date getDateByMillisecond(String dateStr) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        return (Date) formatter.parse(dateStr);
    }

    public static Date strToDate(String dateStr, String dateFormat) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.parse(dateStr);
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

    public static List<Date> listRemoveTime(List<Date> dates) {
        List<Date> dateListResult = new ArrayList<>();

        for (Date date : dates) {
            dateListResult.add(removeTime(date));
        }

        return dateListResult;
    }

    public static List<Date> distinctDate(List<Date> dateList) throws ParseException {
        List<Date> dateListRaw = listRemoveTime(dateList);
        List<Date> dateListResult = new ArrayList<>();

        for (Date date : dateList) {
            dateListResult.add(DateUtils.removeTime(getDateByMillisecond(date.toString())));
        }

        return dateListResult;
    }

}
