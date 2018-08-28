package com.cencosud.scanandgo.order_history.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joseamaro on 20-07-18.
 */

public class DateUtils {

    public static String formaterToString(String pattern, Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static Date formaterToDate(String pattern, String date){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
