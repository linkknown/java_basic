package com.linkknown.java8;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTest {

    @Test
    public void testDate () {
        System.out.println(LocalDate.now());        // 2020-12-11
        System.out.println(LocalTime.now());        // 10:23:26.641 自带毫秒数
        System.out.println(LocalDateTime.now());    // 2020-12-11T10:23:26.641
    }

    @Test
    public void testDate2 () {
        // 获取年月日时分秒
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getYear());
        System.out.println(now.getMonth());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
    }

    @Test
    public void testDate3 () {
        // 设置时间
        LocalDateTime dateTime =
                LocalDateTime.of(2020, 12, 12, 12, 12, 12);
        System.out.println(dateTime);
        // 修改时间
        LocalDateTime dateTime2 = dateTime.withYear(2021).withMonth(1)
                .withDayOfMonth(1).withHour(13).withMinute(13).withSecond(13);
        System.out.println(dateTime2);
    }

    @Test
    public void testDate4 () {
        // 时间比较大小
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDateTime dateTime2 = LocalDateTime.of(2020, 12, 12, 12, 12, 12);
        System.out.println(
                String.format("时间 dateTime1 是否比 时间dateTime2 早：%b ？", dateTime1.isBefore(dateTime2)));
    }

    @Test
    public void testDate5 () {
        // 时间格式化
        LocalDateTime dateTime = LocalDateTime.now();
        String dataTimeStr = dateTime.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dataTimeStr);

        dateTime = LocalDateTime.parse("2020-12-11 10:40:52",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        dataTimeStr = dateTime.format(
                DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒"));
        System.out.println(dataTimeStr);
    }

}
