package com.suhuamo.init.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author suhuamo
 * @slogan 今天的早餐是：早苗的面包、秋子的果酱和观铃的果汁~
 * @date 2024-03-10
 * @description 日期相关工具类
 */
public class DateUtil {

    /**
     * 日期当天第一秒的日期，格式为 yyyy-MM-dd 00:00:00
     * @version 1.0
     * @author chenjing
     * @with {@link }
     */
    public static final SimpleDateFormat dayBeginTimeFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

    /**
     * 日期当天最后一秒的日期，格式为 yyyy-MM-dd 23:59:59
     * @version 1.0
     * @author chenjing
     * @with {@link }
     */
    public static final SimpleDateFormat dayEndTimeFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

    /**
     * 日期，格式为 yyyy-MM-dd
     * @version 1.0
     * @author chenjing
     * @with {@link }
     */
    public static final SimpleDateFormat datFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 使用DateTimeFormatter格式化日期,格式为 yyyy-MM-dd
     * @version 1.0
     * @author chenjing
     * @with {@link }
     */
    public static final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 用来创建名称的时间格式：yyyyMMddHHmmssSSS
     * @version 1.0
     * @author chenjing
     * @with {@link }
     */
    public static final SimpleDateFormat createNameFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 根据当前时间获取名称 yyyyMMddHHmmssSSS
     * @return String
     * @version
     * @author yuanchuncheng
     */
    public static String getNameByDate() {
        return createNameFormat.format(new Date());
    }

    /**
     * 获取这一天的开始时间，格式为 yyyy-MM-dd 00:00:00
     * @param date
     * @return String
     * @version
     * @author yuanchuncheng
     */
    public static String getDayBeginTime(String date) {
        Date parse = null;
        try {
            parse = datFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return getDayBeginTime(parse);
    }

    /**
     * 获取这一天的结束时间，格式为 yyyy-MM-dd 23:59:59
     * @param date
     * @return String
     * @version
     * @author yuanchuncheng
     */
    public static String getDayEndTime(String date) {
        Date parse = null;
        try {
            parse = datFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return getDayEndTime(parse);
    }


    /**
     * 获取这一天的开始时间，格式为 yyyy-MM-dd 00:00:00
     * @param date
     * @return String
     * @version
     * @author yuanchuncheng
     */
    public static String getDayBeginTime(Date date) {
        return dayBeginTimeFormat.format(date);
    }

    /**
     * 获取这一天的结束时间，格式为 yyyy-MM-dd 23:59:59
     * @param date
     * @return String
     * @version
     * @author yuanchuncheng
     */
    public static String getDayEndTime(Date date) {
        return dayEndTimeFormat.format(date);
    }

    /**
     * 计算两个日期之间的小时数，向上取整
     * @param startDateTime
     * @param endDateTime
     * @return long
     * @version
     * @author yuanchuncheng
     */
    public static long calculateHoursBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration duration = Duration.between(startDateTime, endDateTime);
        long hours = duration.toHours(); // 获取小时数
        if (duration.toMinutes() % 60 != 0) { // 如果有不足一个小时的分钟数，向上取整
            hours++;
        }
        return hours;
    }

    /**
     * 获取当天24小时每一小时的时间，和第二天0点的时间 格式为：yyyy-MM-dd HH:mm:ss
     * @param
     * @return List<String>
     * @version
     * @author yuanchuncheng
     */
    public static List<String> get24HoursList() {
        List<String> dayHoursList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        String pattern = "yyyy-MM-dd HH:mm:ss";

        for (int i = 0; i < 24; i++) {
            LocalDateTime currentHour = now.withHour(i).withMinute(0).withSecond(0).withNano(0);
            String formattedHour = currentHour.format(DateTimeFormatter.ofPattern(pattern));
            dayHoursList.add(formattedHour);
            if(i == 24 - 1) {
                LocalDateTime localDateTime = currentHour.plusHours(1);
                String tomorrow = localDateTime.format(DateTimeFormatter.ofPattern(pattern));
                dayHoursList.add(tomorrow);
            }
        }
        return dayHoursList;
    }

    /**
     * 获取这周每天的日期，格式为：yyyy-MM-dd
     * @param
     * @return List<String>
     * @version
     * @author yuanchuncheng
     */
    public static List<String> getThisWeekDataListString() {
        List<String> thisWeekDataListString = new ArrayList<>();
        // 获取今天的日期
        LocalDate today = LocalDate.now();

        // 获取今天是星期几
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        // 计算本周的第一天（通常是星期一）
        LocalDate firstDayOfWeek = today.minusDays(dayOfWeek.getValue() - 1);

        // 添加本周的七天日期
        for (int i = 0; i < 7; i++) {
            LocalDate date = firstDayOfWeek.plusDays(i);
            String formattedDate = date.format(DAY_FORMATTER);
            thisWeekDataListString.add(formattedDate);
        }
        return thisWeekDataListString;
    }

    public static void main(String[] args) {
        String dayBeginTime = getDayBeginTime(new Date());
        System.out.println("dayBeginTime = " + dayBeginTime);
        String dayBeginTime1 = getDayBeginTime("2023-03-24");
        System.out.println("dayBeginTime1 = " + dayBeginTime1);
    }

    private DateUtil(){}
}
