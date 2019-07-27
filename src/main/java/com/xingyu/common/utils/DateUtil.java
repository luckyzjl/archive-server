package com.xingyu.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maobg on 2015/7/14.
 */
public class DateUtil {
    private static Log logger = LogFactory.getLog(DateUtil.class);

    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
    static ThreadLocal<Calendar> threadLocalCalendar = new ThreadLocal<Calendar>();
    static ThreadLocal<Pattern>  threadLocalPattern = new ThreadLocal<>();
    public static String format(Date date){
        SimpleDateFormat dateFormat = (SimpleDateFormat)threadLocal.get();
        if(null == dateFormat){
            dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
            threadLocal.set(dateFormat);
        }
        return dateFormat.format(date);
    }

    public static String format(Date date,String format){
        if(null == format)
            format = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }


    public static Date parse(String date) {
        SimpleDateFormat dateFormat = (SimpleDateFormat)threadLocal.get();
        if(null == dateFormat){
            dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
            threadLocal.set(dateFormat);
        }

        try {
            return dateFormat.parse(date);
        }catch (Exception ex){
            return new Date();
        }
    }

    public static Date getDateZero(Date date){
        Calendar calendar = (Calendar)threadLocalCalendar.get();
        if (null == calendar){
            calendar = Calendar.getInstance();
            threadLocalCalendar.set(calendar);
        }
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public  static Date parse(Date date) {
        try {
            return parse(format(date));
        }catch (Exception ex){
            return new Date();
        }
    }

    public static Date today() throws ParseException {
        SimpleDateFormat dateFormat = (SimpleDateFormat)threadLocal.get();
        if(null == dateFormat){
            dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
            threadLocal.set(dateFormat);
        }
        return dateFormat.parse(dateFormat.format(new Date()));
    }

    public static Boolean isWeekEnd(Date date){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(date);
        return (6==cal.get(Calendar.DAY_OF_WEEK) ||7==cal.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * 获取某一年的年底时间
     */
    public static Date addYearsAndToYearEnd(Date date,Integer yearSpan){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(date);
        cal.add(Calendar.YEAR,yearSpan);
        cal.set(Calendar.MONTH,11);
        cal.set(Calendar.DAY_OF_MONTH,31);
        return cal.getTime();
    }

    public static Date addDays(Date date,Integer daySpan){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR,daySpan);
        return cal.getTime();
    }

    public static Date addMonths(Date date,Integer monthSpan){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(date);
        cal.add(Calendar.MONTH,monthSpan);
        return cal.getTime();
    }

    public static Date addYears(Date date,Integer yearSpan){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(date);
        cal.add(Calendar.YEAR,yearSpan);
        return cal.getTime();
    }

    public static Date addHours(Date date, Integer hours){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY,hours);
        return cal.getTime();
    }

    public static Date addSeconds(Date date,Integer secondSpan){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(date);
        cal.add(Calendar.SECOND,secondSpan);
        return cal.getTime();
    }

    public static Integer day(Date date){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static Integer month(Date date){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static Integer year(Date date){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static Integer dayspan(Date date1, Date date2) {
        String s_date1 = format(date1);
        String s_date2 =  format(date2);

        try {
            Date round_date1   = parse(s_date1);
            Date round_date2   = parse(s_date2);
            Long timespan = round_date2.getTime() - round_date1.getTime();
            return (int)(timespan/ (24 * 60 * 60 * 1000));
//            return (int) Math.abs( (round_date2.getTime() - round_date1.getTime()) / (24 * 60 * 60 * 1000) );
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return 0;
    }

    public static Integer dayspanCeil(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        Long timespan = cal2.getTimeInMillis() - cal1.getTimeInMillis();
        return (int) Math.ceil(timespan.doubleValue()/(24 * 60 * 60 * 1000));
    }

    public static boolean isLastYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);

        Calendar curCal = Calendar.getInstance();
        curCal.setTime(new Date());
        int curYear = curCal.get(Calendar.YEAR);
        return (year < curYear);

    }

    public static int getYear(){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(new Date());
        return cal.get(Calendar.YEAR);
    }

    public static String getMonth(){
        Calendar cal = (Calendar)threadLocalCalendar.get();
        if (null == cal){
            cal = Calendar.getInstance();
            threadLocalCalendar.set(cal);
        }
        cal.setTime(new Date());
        return String.valueOf(cal.get(Calendar.MONTH)+1);
    }

    public static String getYearMonth(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1);
    }

    public static boolean isDate(String str){
        Pattern pattern = (Pattern)threadLocalPattern.get();
        if(null == pattern){
            String path="\\d{4}-\\d{2}-\\d{2}";     //定义匹配规则
            pattern = Pattern.compile(path);
            threadLocalPattern.set(pattern);
        }
        Matcher m = pattern.matcher(str);             //验证字符串内容是否合法
        if(m.matches())                               //使用正则验证
        {
            return true;
        }
        else
        {
            return false;
        }
    }


//    public static void main(String[] args) throws Exception {
//        if (isDate("2019-01-01")){
//            System.out.printf("true");
//        }
//
//    }

}
