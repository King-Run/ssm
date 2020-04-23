package com.zhku.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换的工具类
 */
public class DateUtils {
    /**
     *
     * @param date 传入要转换的日期
     * @param patt 传入要转换的格式
     * @return
     */
    public static String DateToString(Date date, String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }
    public static Date StringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;
    }
}
