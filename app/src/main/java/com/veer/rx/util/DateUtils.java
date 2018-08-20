package com.veer.rx.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * 作者：veer
 * 日期：2017/8/17 13:49
 * 功能：日期、时间工具类
 */
public class DateUtils {

    //缓存时间
    public static int TIME_YEAR =15552000; //60 * 60 * 24 *180 单位(秒)
    public static int TIME_HOUR =3600; //60 * 60 * 1 单位(秒)
    public static int TIME_DAY =86400; //60 * 60 * 24 单位(秒)   /1440
    /**
     * 指定日期格式 yyyyMMddHHmmss
     */
    public static final String DATE_FORMAT_1 = "yyyyMMddHHmmss";

    /**
     * 指定日期格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 指定日期格式 yyyy-MM-dd'T'HH:mm:ssZ
     */
    public static final String DATE_FORMAT_3 = "yyyy-MM-dd'T'HH:mm:ssZ";

    /**
     * 指定日期格式 yyyy-MM-dd
     */
    public static final String DATE_FORMAT_4 = "yyyy-MM-dd";

    /**
     * 指定日期格式 yyyy.M.d
     */
    public static final String DATE_FORMAT_5 = "yyyy.M.d";

    /**
     * 指定日期格式yyyy-MM-dd HH:mm
     */
    public static final String DATE_FORMAT_6 = "yyyy-MM-dd HH:mm";

    /**
     * 指定日期格式HH:mm
     */
    public static final String DATE_FORMAT_7 = "HH:mm";

    /**
     * 指定日期格式MM-dd HH:mm
     */
    public static final String DATE_FORMAT_8 = "MM-dd HH:mm";

    /**
     * 指定日期格式HH:MM:SS
     */
    public static final String DATE_FORMAT_9 = "HH:MM:SS";

    /**
     * 指定日期格式HH:mm
     */
    public static final String DATE_FORMAT_10 = "MM-dd";

    /**
     * 指定日期格式yy-MM-dd HH:mm
     */
    public static final String DATE_FORMAT_11 = "yy-MM-dd HH:mm";

    /**
     * 日期排序类型-升序
     */
    public final static int DATE_ORDER_ASC = 0;

    /**
     * 日期排序类型-降序
     */
    public final static int DATE_ORDER_DESC = 1;

    /**
     * 根据指定格式，获取现在时间
     */
    public static String getNowDateFormat(String format) {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(currentTime);
    }

    /**
     * 把String日期转成制定格式
     */
    public static String getDateFormat(String getDateString, String format) {
        if (!TextUtils.isEmpty(getDateString)) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

            Date getDate = null;
            try {
                getDate = getFormat(DATE_FORMAT_6).parse(getDateString);
            } catch (ParseException e) {
                e.printStackTrace();
                return getDateString;
            }

            return simpleDateFormat.format(getDate);
        }
        return getDateString;
    }


    /**
     * 根据时间戳转成指定的format格式
     *
     * @param timeMillis
     * @param format
     * @return
     */
    public static String formatDate(String timeMillis, String format) {
        Date date = null;
        if (!TextUtils.isEmpty(timeMillis)) {
            try {
                date = new Date(Long.parseLong(timeMillis));
            } catch (NumberFormatException e) {
                date = new Date();
            }
        } else {
            date = new Date();
        }

        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static String formatDate(long timeMillis, String format) {
        Date date = null;
        if (timeMillis > 0) {
            date = new Date(timeMillis);
        } else {
            date = new Date();
        }

        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * format处理
     *
     * @param getDateString
     * @return
     */
    public static String getDateFormatForLetter(String getDateString,
                                                Context context) {

        if (TextUtils.isEmpty(getDateString)) {
            return "";
        }

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");

        Date getDate = null;
        try {
            getDate = getFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(getDateString);
        } catch (ParseException e) {
            getDate = new Date();
        }

        return simpleDateFormat.format(getDate);
    }

    /**
     * 当前时间计算
     *
     * @param getDateString
     * @return
     */
    public static String getTimeStringDisplay(String getDateString) {
        Date getDate = null;
        try {
            getDate = getFormat(DATE_FORMAT_6).parse(getDateString);
        } catch (ParseException e) {
            e.printStackTrace();
            getDate = new Date();
        }
        final long getTime = getDate.getTime();

        final long currTime = System.currentTimeMillis();
        final Date formatSysDate = new Date(currTime);

        if (getDate.getDate() < formatSysDate.getDate()) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    DATE_FORMAT_10);
            return simpleDateFormat.format(getDate);
        } else {
            // 判断当前总天数
            final int sysMonth = formatSysDate.getMonth() + 1;
            final int sysYear = formatSysDate.getYear();

            // 计算服务器返回时间与当前时间差值
            final long seconds = (currTime - getTime) / 1000;
            final long minute = seconds / 60;
            final long hours = minute / 60;
            final long day = hours / 24;
            final long month = day / calculationDaysOfMonth(sysYear, sysMonth);
            final long year = month / 12;

            if (year > 0 || month > 0 || day > 0) {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                        DATE_FORMAT_10);
                return simpleDateFormat.format(getDate);
            } else if (minute > 2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                        DATE_FORMAT_7);
                return simpleDateFormat.format(getDate);
            } else {
                return ""; //都换成分钟前
            }
        }

    }


    /**
     * 当前时间计算
     *
     * @param getDateString
     * @return
     */
    public static String getTimeDisplay(String getDateString) {
        return getTimeDisplay(getDateString, true);
    }

    public static String getTimeDisplay(String getDateString, boolean timeZone) {
        Date getDate = null;
        try {
            if (getDateString.contains("T")) {
                if (timeZone) {
                    getDate = getFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(getDateString);
                } else {
                    getDate = getFormat("yyyyMMdd'T'HH:mm:ss").parse(getDateString);
                }
            } else {
                getDate = getFormat(DATE_FORMAT_6).parse(getDateString);
            }

        } catch (ParseException e) {
            e.printStackTrace();
            getDate = new Date();
        }

        final long getTime = getDate.getTime();

        final long currTime = System.currentTimeMillis();
        final Date formatSysDate = new Date(currTime);

        // 判断当前总天数
        final int sysMonth = formatSysDate.getMonth() + 1;
        final int sysYear = formatSysDate.getYear();

        // 计算服务器返回时间与当前时间差值
        final long seconds = (currTime - getTime) / 1000;
        final long minute = seconds / 60;
        final long hours = minute / 60;
        final long day = hours / 24;
        final long month = day / calculationDaysOfMonth(sysYear, sysMonth);
        final long year = month / 12;

        if (year > 0 || month > 0 || day > 0) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm");
            return simpleDateFormat.format(getDate);
        } else if (hours > 0) {
            return hours + "";
        } else if (minute > 0) {
            return minute + "";
        } else if (seconds > 0) {
            return seconds + "";
        } else {
            return "";
        }
    }

    public static SimpleDateFormat getFormat(String partten) {
        return new SimpleDateFormat(partten);
    }

    public static String getFormatDataString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String parseJsonDate(String date) {
        return date.substring(0, 10);
    }

    /**
     * 计算月数
     *
     * @return
     */
    private static int calculationDaysOfMonth(int year, int month) {
        int day = 0;
        switch (month) {
            // 31天
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            // 30天
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            // 计算2月天数
            case 2:
                day = year % 100 == 0 ? year % 400 == 0 ? 29 : 28
                        : year % 4 == 0 ? 29 : 28;
                break;
        }

        return day;
    }

    /**
     * 日期排序
     *
     * @param dates     日期列表
     * @param orderType 排序类型：DATE_ORDER_ASC，DATE_ORDER_DESC
     * @return 排序后的list
     * <p>
     * 用法 ArrayList<Date> dates = new ArrayList<Date>(); String dateStr
     * = "2011-10-25T00:00:00+08:00"; Date getDate =
     * getFormat("yyyy-MM-dd").parse(dateStr); dates.add(getDate);
     * <p>
     * orderDate(dates, DATE_ORDER_ASC);
     */
    public static ArrayList<Date> orderDate(ArrayList<Date> dates, int orderType) {
        final DateComparator comp = new DateComparator(orderType);
        Collections.sort(dates, comp);
        return dates;
    }

    private static class DateComparator implements Comparator<Date> {
        int orderType;

        public DateComparator(int orderType) {
            this.orderType = orderType;
        }

        @Override
        public int compare(Date d1, Date d2) {
            if (d1.getTime() > d2.getTime()) {
                if (orderType == DATE_ORDER_ASC) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                if (d1.getTime() == d2.getTime()) {
                    return 0;
                } else {
                    if (orderType == DATE_ORDER_DESC) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        }
    }

    /***
     * 返回日期 ，如果参数为0返回今天。
     * */
    public static String getNowDate(int afterDay) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, afterDay);
        return dateFormat.format(calendar.getTime());
    }

    public static Calendar getNowCalendar(int afterDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, afterDay);
        return calendar;
    }

    /**
     * 计算天数方法
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDaysBetween(String start, String end) {
        int days = 0;

        boolean isNegative = false;
        if (!TextUtils.isEmpty(start) && !TextUtils.isEmpty(end)) {
            try {
                final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                final Date startDate = format.parse(start);

                Calendar startTime = Calendar.getInstance();
                startTime.clear();
                startTime.setTime(startDate);

                final Date endDate = format.parse(end);
                Calendar endTime = Calendar.getInstance();
                endTime.clear();
                endTime.setTime(endDate);

                if (startTime.after(endTime)) {
                    java.util.Calendar swap = startTime;
                    startTime = endTime;
                    endTime = swap;
                    isNegative = true;

                }
                days = endTime.get(Calendar.DAY_OF_YEAR)
                        - startTime.get(Calendar.DAY_OF_YEAR);
                final int y2 = endTime.get(Calendar.YEAR);
                if (startTime.get(Calendar.YEAR) != y2) {
                    startTime = (Calendar) startTime.clone();
                    do {
                        days += startTime
                                .getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                        startTime.add(Calendar.YEAR, 1);
                    } while (startTime.get(Calendar.YEAR) != y2);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        if (isNegative && days != 0) {
            return -days;
        } else {
            return days;
        }
    }

    /**
     * 计算两个时间相隔分钟
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long getIntervalMinutes(String startTime, String endTime) {
        if (!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {
            try {
                final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                final Date startDate = format.parse(startTime);
                final Calendar start = Calendar.getInstance();
                start.clear();
                start.setTime(startDate);

                final Date endDate = format.parse(endTime);
                final Calendar end = Calendar.getInstance();
                end.clear();
                end.setTime(endDate);

                // 把时间转成毫秒
                final long time1 = start.getTimeInMillis();
                final long time2 = end.getTimeInMillis();

                // 计算两个时间相差多少毫秒
                final long diff = time1 - time2;

                // 把相差的毫秒转成分钟
                final long diffMin = diff / (60 * 1000);

                {/*
                // 相差小时
                long diffHours = diff / (60 * 60 * 1000);
                System.out.println("Difference in hours " + diffHours);

                // 相差天
                long diffDays = diff / (24 * 60 * 60 * 1000);
            */
                }

                return diffMin;
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return 0;
    }

    /**
     * 把yyyy-MM-dd'T'HH:mm:ssZ类型日期转成yyyy.MM.dd类型
     *
     * @param str
     * @return
     */
    public static String parseStrToDate(String str) {
        if (str != null && str.length() > 0) {
            Date date = null;
            try {
                date = getFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(str);
            } catch (Exception ex) {
            }
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            return simpleDateFormat.format(date);
        } else {
            return null;
        }
    }

    /**
     * 根据日期获取星期
     *
     * @param str
     * @return
     */
    public static String getWeekday(String str, Context context) {
        try {
            final Date date = getFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(str);
            if (date != null) {
                return weekdayFormat(date.getDay(), context); // 0..6 0:sunday,
            }
        } catch (ParseException e) {
        }
        return "";
    }

    private static String weekdayFormat(int day, Context context) {
        switch (day) {
            case 0:
                return "周日";
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            default:
                return "";
        }
    }

    /**
     * 获取日期显示
     *
     * @param endTime
     * @return
     */
    public static String getDateIncludeWeek(Calendar endTime) {
        final int afterDay = getDaysBetween(endTime);
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Calendar calendar = Calendar.getInstance();
        // calendar.add(Calendar.DAY_OF_MONTH, afterDay);
        switch (afterDay) {
            case -1:
                return dateFormat.format(endTime.getTime());
            case 0:
                return dateFormat.format(endTime.getTime()) + " 今天";
            case 1:
                return dateFormat.format(endTime.getTime()) + " 明天";
            case 2:
                return dateFormat.format(endTime.getTime()) + " 后天";
        }
        return dateFormat.format(endTime.getTime()) + " " + getWeekDay(endTime);
    }

    /**
     * 去掉星期只截取日期
     *
     * @param temp
     * @return
     */
    public static String getDateNoWeek(String temp) {
        if (temp != null && temp.length() > 10) {
            return temp.substring(0, 10);
        }
        return temp;
    }

    public static String getDateNoWeek(TextView tv) {
        if (tv != null) {
            final String temp = tv.getText().toString();
            return getDateNoWeek(temp);
        }
        return "";
    }

    /**
     * 把2012-02-20 星期一 转化Date 对象
     *
     * @param temp
     * @return
     */
    public static Date String2Date(String temp) {
        final String str = getDateNoWeek(temp);
        if (str != null && str.length() == 10) {
            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                return format.parse(str);
            } catch (ParseException e) {
            }

        }
        return null;
    }

    /**
     * 返回指定格式的date对象
     *
     * @param strDate
     * @param strFormat
     * @return
     */
    public static Date getString2FormatDate(String strDate, String strFormat) {
        if (!TextUtils.isEmpty(strDate)) {
            try {
                final SimpleDateFormat format = new SimpleDateFormat(strFormat);
                return format.parse(strDate);
            } catch (ParseException e) {
            }
        }
        return null;
    }

    /**
     * 从当天开始计算天数
     *
     * @return
     */
    public static int getDaysBetween(Calendar endTime) {
        int days = 0;
        boolean isNegative = false;
        Calendar startTime = Calendar.getInstance();
        try {
            if (startTime.after(endTime)) {
                java.util.Calendar swap = startTime;
                startTime = endTime;
                endTime = swap;
                isNegative = true;
            }
            days = endTime.get(Calendar.DAY_OF_YEAR) - startTime.get(Calendar.DAY_OF_YEAR);
            final int y2 = endTime.get(Calendar.YEAR);
            if (startTime.get(Calendar.YEAR) != y2) {
                startTime = (Calendar) startTime.clone();
                do {
                    days += startTime.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                    startTime.add(Calendar.YEAR, 1);
                } while (startTime.get(Calendar.YEAR) != y2);
            }
        } catch (Exception e) {
        }

        if (isNegative && days != 0) {
            return -days;
        } else {
            return days;
        }
    }

    /**
     * 获取日期星期
     *
     * @param c
     * @return
     */
    private static String getWeekDay(Calendar c) {
        if (c == null) {
            return "周一";
        }
        if (Calendar.MONDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周一";
        }
        if (Calendar.TUESDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周二";
        }
        if (Calendar.WEDNESDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周三";
        }
        if (Calendar.THURSDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周四";
        }
        if (Calendar.FRIDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周五";
        }
        if (Calendar.SATURDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周六";
        }
        if (Calendar.SUNDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return "周日";
        }
        return "星期一";
    }

    /**
     * 用于计算两个"2012-02-12  星期一"格式 日期之间间隔天数
     *
     * @param start
     * @param end
     * @return
     * @author Administrator
     */
    public static String getDaysBetweenForContainWeek(String start, String end) {
        return getDaysBetween(getDateNoWeek(start), getDateNoWeek(end)) + "天";
    }

    /**
     * 用于计算两个"2012-02-12  星期一"格式 日期之间间隔天数
     *
     * @param start
     * @param end
     * @return
     * @author Administrator
     */
    public static String getDaysBetweenForContainWeek(TextView start,
                                                      TextView end) {
        return getDaysBetween(getDateNoWeek((start.getText().toString())),
                getDateNoWeek((end.getText().toString()))) + "天";
    }

    /**
     * 将TextView里字符串转换成Calendar
     */
    public static Calendar viewText2Calendar(TextView tv) {
        final String date = tv != null ? tv.getText().toString() : "";
        return string2Calendar(date);
    }

    /**
     * 将字符串转换成Calendar
     *
     * @param date
     * @return
     */
    public static Calendar string2Calendar(String date) {
        date = getDateNoWeek(date);
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            d = new Date();
        }
        final Calendar mCalendar = (Calendar) Calendar.getInstance().clone();
        mCalendar.setTime(d);
        return mCalendar;
    }

    /**
     * 通过2012-03-07 获取 2012-03-07 星期三
     *
     * @param endTime
     * @return
     */
    public static String getDateIncludeWeek(String endTime) {
        final Calendar endTimeCalendar = string2Calendar(endTime);
        return getDateIncludeWeek(endTimeCalendar);
    }

    /**
     * 时间大小比较
     *
     * @param oldDate
     * @param defDate
     * @return oldDate小于defDate
     */
    public static boolean compareToDate(String oldDate, String defDate) {
        if (TextUtils.isEmpty(oldDate) || TextUtils.isEmpty(defDate)) {
            return true;
        }

        final Calendar c1 = Calendar.getInstance();
        final Calendar c2 = Calendar.getInstance();

        try {
            final DateFormat df = new SimpleDateFormat(DATE_FORMAT_8);
            c1.setTime(df.parse(oldDate));
            c2.setTime(df.parse(defDate));
        } catch (Exception e) {
        }

        final int result = c1.compareTo(c2);
        if (result < 0) {
            return true;
        }
        return false;
// if (result == 0) {
// DXLogUtil.e(“c1相等c2”);
// } else if(result<0) {
// DXLogUtil.e(“c1小于c2”);
// } else {
// DXLogUtil.e(“c1大于c2”);
// }
    }

    /**
     * 判断指定日期是否为今天
     *
     * @param txtDate 格式必须为DATE_FORMAT_6
     * @return
     */
    public static boolean isToday(String txtDate) {
        if (TextUtils.isEmpty(txtDate)) {
            return false;
        }


        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_6);
            Date workDay = format.parse(txtDate);

            return isToday(workDay);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断指定日期是否为今天
     *
     * @param workDay 指定的日期
     * @return
     */
    public static boolean isToday(Date workDay) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        Date currTime = new Date();

        c1.setTime(workDay);
        c2.setTime(currTime);

        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断指定时间和当前时间是否小于minute分钟
     *
     * @param txtDate 指定的时间
     * @return
     */
    public static boolean isTimeMatch(String txtDate, int minute) {
        if (TextUtils.isEmpty(txtDate)) {
            return false;
        }

        if (minute == 0) {
            minute = 3;
        }

        try {
            final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_2);
            final Date workDay = format.parse(txtDate);

            final Calendar c1 = Calendar.getInstance();
            final Calendar c2 = Calendar.getInstance();

            final Date currTime = new Date();

            c1.setTime(workDay);
            c2.setTime(currTime);

            if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                    && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
                    && c1.get(Calendar.HOUR_OF_DAY) == c2.get(Calendar.HOUR_OF_DAY)) {
                if (Math.abs(c1.get(Calendar.MINUTE) - c2.get(Calendar.MINUTE)) < minute) {
                    return true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断昨天，今天
     *
     * @param date
     * @return 昨天=今天0点前24小时至今天0点，
     */
    public static String formatMyTweetDetailTime(String date) {

        int day = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date d1 = new Date();//当前时间

            Date d2 = sdf.parse(date);//传进的时间
//
            sdf.format(d2);

            long cha = d2.getTime() - d1.getTime();

            day = new Long(cha / (1000 * 60 * 60 * 24)).intValue();

            if (day == 0) {
                return "今天";
            } else if (day == -1) {
                return "昨天";
            } else {
                if (sdf != null) {

                    final Calendar mCalendar = Calendar.getInstance();
                    mCalendar.setTimeInMillis(d2.getTime());
                    int month = mCalendar.get(Calendar.MONTH) + 1;
                    int dayTime = mCalendar.get(Calendar.DAY_OF_MONTH);
                    if (dayTime < 10) {
                        return month + "月0" + dayTime + "日";//formatTweetDetailTime(date, false);
                    } else {
                        return month + "月" + dayTime + "日";
                    }
                }
// return d2.getMonth()+”月”+d2.getDay()+”日”;//formatTweetDetailTime(date, false);
            }

        } catch (ParseException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return null;

    }


    /**
     * 比较两个日期是否是同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDayCompareDate(String date1, String date2) {
        if (TextUtils.isEmpty(date1) || TextUtils.isEmpty(date2)) {
            return false;
        }
        final Calendar c1 = Calendar.getInstance();
        final Calendar c2 = Calendar.getInstance();

        try {
            final DateFormat df = new SimpleDateFormat(DATE_FORMAT_4);
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (Exception e) {
        }

        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断指定日期是否为今天
     *
     * @param txtDate 格式必须为DATE_FORMAT_6
     * @return
     */
    public static boolean isSameYearCompareDate(String txtDate) {
        if (TextUtils.isEmpty(txtDate)) {
            return false;
        }


        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_6);
            Date workDay = format.parse(txtDate);

            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();

            Date currTime = new Date();

            c1.setTime(workDay);
            c2.setTime(currTime);

            if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 比较两个日期是否是同一年
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameYearCompareDate(String date1, String date2) {
        if (TextUtils.isEmpty(date1) || TextUtils.isEmpty(date2)) {
            return false;
        }

        final Calendar c1 = Calendar.getInstance();
        final Calendar c2 = Calendar.getInstance();

        try {
            final DateFormat df = new SimpleDateFormat(DATE_FORMAT_4);
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (Exception e) {
        }

        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            return true;
        } else {
            return false;
        }

    }
}
