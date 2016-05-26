package com.example.pavel.moneyflow.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateConverter {

    public static String convertToString(long timeInMillis){
        Date date = new Date(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        dateFormat.format(date);
        return dateFormat.toString();
    }

    public static String convertToString(String timeInMillis){
        long time = Long.parseLong(timeInMillis);
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        return dateFormat.format(date);
    }
}
