package com.cv.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date StringToDate(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateStr);
        return date;
    }
    public static Date getToday(){
        return new Date(System.currentTimeMillis());
    }
    public static Date getLastDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDay = c.getTime();
        return lastDay;
    }
    public static String DateToString(Date d)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(d);

    }

    public static void main(String[] args) {
        Date t =getToday();
        int r = 500;
        while(r-->0)
        {
            System.out.println(DateToString(t));
            t = getLastDay(t);

        }
    }
}
