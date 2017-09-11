package com.example.xu.myapplication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by yangguang on 16/3/27.
 */
public class TimeUtil {

    /**
     * 将毫秒数装换成pattern这个格式，我这里是转换成年月日
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String parseTimeToYMD(long time, String pattern) {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(time));
    }


    public static final String TODAY = "today";
    public static final String YESTERDAY = "yesterday ";
    public static final String BEFORE_YESTERDAY = "before_yesterday ";

    public static String format(Date date) {
        SimpleDateFormat formatBuilder;
        Calendar today = Calendar.getInstance();
        Calendar target = Calendar.getInstance();
        today.setTime(new Date());
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        target.setTime(date);
        target.set(Calendar.HOUR, 0);
        target.set(Calendar.MINUTE, 0);
        target.set(Calendar.SECOND, 0);
        long intervalMilli = target.getTimeInMillis() - today.getTimeInMillis();
        int xcts = (int) (intervalMilli / (24 * 60 * 60 * 1000));
        String pre = showDateDetail(xcts);
        if (pre == null) {
            formatBuilder = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            return formatBuilder.format(date);
        } else {
            formatBuilder = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            return pre + formatBuilder.format(date);
        }
    }

    public static String formatForReport(Date date) {
        SimpleDateFormat formatBuilder;
        formatBuilder = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
        return formatBuilder.format(date);

    }

    public static Date format(String date) {
        Date result = null;
        SimpleDateFormat formatBuilder = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            result = formatBuilder.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date format1(String date) {
        Date result = null;
        SimpleDateFormat formatBuilder = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
        try {
            result = formatBuilder.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String showDateDetail(int xcts) {
        switch (xcts) {
            case 0:
                return TODAY;
            case -1:
                return YESTERDAY;
            case -2:
                return BEFORE_YESTERDAY;
            default:
                return null;
        }
    }

    public static String formatWithYearMonthDay(Date date) {
        SimpleDateFormat formatBuilder = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        return formatBuilder.format(date);
    }

    public static String formatToNetDay(Date date) {
        SimpleDateFormat formatBuilder = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return formatBuilder.format(date);
    }

    public static Date parseLong2Date(Long time) {
        return new Date(time);
    }

    public static Date parseNetDay(String date) {
        Date result = null;
        SimpleDateFormat formatBuilder = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            result = formatBuilder.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date parseYYYYMMDDDate(String date) {
        Date result = null;
        SimpleDateFormat formatBuilder = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        try {
            result = formatBuilder.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date parseWithYearMonthDay(String day) {
        Date result = null;
        SimpleDateFormat formatBuilder = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        try {
            result = formatBuilder.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * according year and month,get the days' number
     */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static Date parseGMTDate(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.UK);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getWeekDay(Date date) {
        String[] weekDays = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    public static String getToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar.get(Calendar.DAY_OF_MONTH) + "";
    }

    public static String geMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
    }


    public static String format(int year, int month, int day) {
        String value = String.format(Locale.getDefault(), "%04d/%02d/%02d", year, month, day);
        return value;
    }

    public static String getMinute(long second) {
        String time = "";
        int m = (int) second / 60;
        int s = (int) (second - m * 60);
        time = m + ":" + s;
        return time;
    }

    public static int[] getArrayFromDateStr(String dateStr) {
        String[] array = dateStr.split("/");
        int[] intArray = null;
        if (null != array && array.length == 3) {
            int year = Integer.parseInt(array[0]);
            int month = Integer.parseInt(array[1]);
            int day = Integer.parseInt(array[2]);
            if (0 != year && 0 != month && 0 != day) {
                intArray = new int[3];
                intArray[0] = year;
                intArray[1] = month;
                intArray[2] = day;
            }
        }
        return intArray;
    }

    /**
     * 获取小时分钟秒
     * @param time
     * @return
     */
    public static String getTime(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(new Date(time));
    }

}
