package com.example.demo.config;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 格式化器
 * @author lvqi
 * @since 2021/7/14 17:50
 */
public class CustomDateTimeFormatter implements Formatter<Date> {
    private Pattern p = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}(\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}(:\\d{1,3})?)?");
    @Override
    public Date parse(String date, Locale locale) throws ParseException {
        date = date.trim();
        if(date.length()==0)
            return null;
        if(p.matcher(date).matches()){
            int space = date.indexOf(" ");
            String[] dates;
            if(space<0)
                dates = date.split("-");
            else
                dates = date.substring(0,space).split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,Integer.parseInt(removeLeadingZero(dates[0])));
            calendar.set(Calendar.MONTH,Integer.parseInt(removeLeadingZero(dates[1]))-1);
            calendar.set(Calendar.DATE,Integer.parseInt(removeLeadingZero(dates[2])));
            if(space>0){
                String time = date.substring(date.indexOf(" ")).trim();
                String[] times = time.split(":");
                calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(removeLeadingZero(times[0])));
                calendar.set(Calendar.MINUTE,Integer.parseInt(removeLeadingZero(times[1])));
                calendar.set(Calendar.SECOND,Integer.parseInt(removeLeadingZero(times[2])));
                if(times.length==4)
                    calendar.set(Calendar.MILLISECOND,Integer.parseInt(removeLeadingZero(times[3])));
                else
                    calendar.set(Calendar.MILLISECOND,0);
            }else{
                calendar.set(Calendar.HOUR_OF_DAY,0);
                calendar.set(Calendar.MINUTE,0);
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
            }
            return calendar.getTime();
        }
        throw new ParseException(date,0);
    }

    private static String removeLeadingZero(String str){
        char[] chars = str.toCharArray();
        int point=0;
        for (int i = 0; i < chars.length;i++) {
            if(chars[i]=='0')
                point = i+1;
            else
                break;
        }
        return new String(chars,point,chars.length-point);
    }

    @Override
    public String print(Date date, Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",locale);
        return format.format(date);
    }
}
