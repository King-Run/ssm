package com.zhku.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String,Date> {

    @Override
    public Date convert(String s) {
        if(s == null){
            throw new RuntimeException("参数不能为空");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = format.parse(s);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException("数据转换失败");
        }

    }
}
