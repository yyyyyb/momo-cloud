package com.momo.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 获取当前日期(格式：yyyy-MM-dd)
     * @return date
     */
    public static Date getNowDateY() {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(dateFormat.format(new Date()));
        } catch (Exception e) {
            System.out.println("转换出错");
        }
        return date;
    }
    /**
     * yyyy-MM-dd日期字符串
     * @param date 日期
     * @return str
     */
    public static String dateToStr(Date date) {
        String strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        strDate = format.format(date);
        return strDate;
    }

    /**
     * yyyy-MM-dd HH:mm:ss日期字符串
     * @param date
     * @return
     */
    public static String dateToStrS(Date date) {
        String strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strDate = format.format(date);
        return strDate;
    }

    /**
     * 日期显示替换
     * @param date
     * @return
     */
    public static String dateReplace(String date) {
        String replaceStr = date;
        if (StringUtils.isNotBlank(date)) {
            if (date.contains(" ")) {
                replaceStr = replaceStr.replace(" ", "");
            }
            if (date.contains("d")) {
                replaceStr = replaceStr.replace("d", "天");
            }
            if (date.contains("h")) {
                replaceStr = replaceStr.replace("h", "时");
            }
            if (date.contains("m")) {
                replaceStr = replaceStr.replace("m", "分");
            }
            if (date.contains("s")) {
                replaceStr = replaceStr.replace("s", "秒");
            }
        }
        return replaceStr;
    }

    /**
     * 日期时区转换yyyy-MM-dd
     * @param utcTime
     * @return
     */
    public static String dateChangeStr(String utcTime) {
        String date = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date newDate = df.parse(utcTime);
            date = dateToStr(newDate);
        } catch (Exception e) {
            System.out.println("转换出错"+ utcTime);
        }
        return date;
    }

    /**
     * 日期时区转换yyyy-MM-dd HH:mm:ss
     * @param utcTime
     * @return
     */
    public static String dateChangeStrS(String utcTime) {
        String date = "";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date newDate = df.parse(utcTime);
            date = dateToStrS(newDate);
        } catch (Exception e) {
            System.out.println("转换出错"+ utcTime);
        }
        return date;
    }

    /**
     * 获取当前时间距离今天晚上23.59.59的秒数
     * @return
     */
    public static Long getTodayRemainSecond() {
        Long remain = null;
        try {
            long now = System.currentTimeMillis();
            SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
            long overTime = (now - (sdfOne.parse(sdfOne.format(now)).getTime())) / 1000;
            //当前时间  距离当天晚上23:59:59  秒数 也就是今天还剩多少秒
            remain = 24 * 60 * 60 - overTime;
        } catch (Exception e) {
            System.out.println("获取出错");
        }
        return remain;
    }
}
