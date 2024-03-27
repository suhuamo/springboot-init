package com.suhuamo.init.util;

import java.text.SimpleDateFormat;
import java.time.Duration;
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
     * 用来创建名称的时间格式：yyyyMMddHHmmssSSS
     * @version 1.0
     * @author chenjing
     * @with {@link }
     */
    public static final SimpleDateFormat createNameFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static String getNameByDate() {
        return createNameFormat.format(new Date());
    }

    public static String getDayBeginTime(Date date) {
        return dayBeginTimeFormat.format(date);
    }

    public static String getDayEndTime(Date date) {
        return dayEndTimeFormat.format(date);
    }

    public static long calculateHoursBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Duration duration = Duration.between(startDateTime, endDateTime);
        long hours = duration.toHours(); // 获取小时数
        if (duration.toMinutes() % 60 != 0) { // 如果有不足一个小时的分钟数，向上取整
            hours++;
        }
        return hours;
    }

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

    private DateUtil(){}
}
