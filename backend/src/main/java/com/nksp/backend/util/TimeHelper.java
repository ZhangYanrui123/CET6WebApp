package com.nksp.backend.util;

import java.util.Calendar;
import java.util.Date;

public class TimeHelper {
    public static String getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);					//放入Date类型数据

        int year = calendar.get(Calendar.YEAR);					//获取年份
        int month = calendar.get(Calendar.MONTH);					//获取月份
        int day = calendar.get(Calendar.DATE);					//获取日

        int hour12 = calendar.get(Calendar.HOUR);					//时（12小时制）
        int hour = calendar.get(Calendar.HOUR_OF_DAY);				//时（24小时制）
        int minute = calendar.get(Calendar.MINUTE);					//分
        int second = calendar.get(Calendar.SECOND);					//秒

        int wday = calendar.get(Calendar.DAY_OF_WEEK);				//一周的第几天

        return  month + "月" + day + "日" + hour + ":" + minute + ":" + second;
    }
}
