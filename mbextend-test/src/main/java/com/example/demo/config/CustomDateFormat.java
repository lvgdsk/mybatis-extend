package com.example.demo.config;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lvgdsk
 * @version 1.0.0
 * @since 2021-07-19 23:18:00
 */
public class CustomDateFormat extends SimpleDateFormat {

    private CustomDateTimeFormatter formatter = new CustomDateTimeFormatter();

    public CustomDateFormat(){
        super("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public Date parse(String source) {
        try {
            return formatter.parse(source, null);
        }catch (Exception e) {
            try {
                return new StdDateFormat().parse(source);//支持解析long类型的时间戳
            } catch (ParseException e1) {
                throw new RuntimeException("日期格式非法：" + e);
            }
        }
    }
}
