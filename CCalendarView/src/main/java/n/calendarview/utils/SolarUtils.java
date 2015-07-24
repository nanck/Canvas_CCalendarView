package n.calendarview.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 计算二十四节气
 * Created by nanck on 15/7/23.
 */
public class SolarUtils {
    //
    public static int SOLAR_TERMS_COUNT = 24;

    public static final int VERNAL_EQUINOX = 0;  // 春分
    public static final int CLEAR_AND_BRIGHT = 1;    // 清明
    public static final int GRAIN_RAIN = 2;    // 谷雨
    public static final int SUMMER_BEGINS = 3;    // 立夏
    public static final int GRAIN_BUDS = 4;    // 小满
    public static final int GRAIN_IN_EAR = 5;    // 芒种
    public static final int SUMMER_SOLSTICE = 6;    // 夏至
    public static final int SLIGHT_HEAT = 7;    // 小暑
    public static final int GREAT_HEAT = 8;    // 大暑
    public static final int AUTUMN_BEGINS = 9;    // 立秋
    public static final int STOPPING_THE_HEAT = 10;   // 处暑
    public static final int WHITE_DEWS = 11;   // 白露
    public static final int AUTUMN_EQUINOX = 12;   // 秋分
    public static final int COLD_DEWS = 13;   // 寒露
    public static final int HOAR_FROST_FALLS = 14;   // 霜降
    public static final int WINTER_BEGINS = 15;   // 立冬
    public static final int LIGHT_SNOW = 16;   // 小雪
    public static final int HEAVY_SNOW = 17;   // 大雪
    public static final int WINTER_SOLSTICE = 18;   // 冬至
    public static final int SLIGHT_COLD = 19;   // 小寒
    public static final int GREAT_COLD = 20;   // 大寒
    public static final int SPRING_BEGINS = 21;   // 立春
    public static final int THE_RAINS = 22;   // 雨水
    public static final int INSECTS_AWAKEN = 23;   // 惊蛰

    static final long[] lunarInfo = new long[]
            {0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,
                    0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,
                    0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
                    0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,
                    0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,
                    0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
                    0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,
                    0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
                    0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570,
                    0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
                    0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,
                    0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,
                    0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
                    0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
                    0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0};
    static final String lunarMonth[] = {"正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "腊"};
    static final String lunarDay[] = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};

    private boolean leap = false;
    private int day;
    private int month;
    private int year;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

    public SolarUtils() {
    }

    //构造函数的作用是通过对下面四个辅助函数的使用，再结合参数cal，对year，month
    //day，leap进行赋值，
    public SolarUtils(Calendar cal) {
        int offset;
        int leapMonth = 0;
        int days = 0;

        Date baseDate = null;

        try {
            baseDate = dateFormat.parse("1900年1月31日");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        offset = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L) + 1;

        //以下是对year进行赋值
        int iYear;
        for (iYear = 1900; iYear < 2050 && offset > 0; iYear++) {
            days = daysOfYear(iYear);
            offset -= days;
        }

        if (offset <= 0) {
            offset += days;
            iYear--;
        }
        year = iYear;
        //对year赋值结束

        //以下是对month，day，leap进行赋值
        leapMonth = monthOfLeap(iYear);
        leap = false;

        int iMonth;
        for (iMonth = 1; iMonth <= 12 && offset > 0; iMonth++) {
            if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap) {
                iMonth--;
                leap = true;
                days = daysOfLeapMonth(year);
            } else {
                days = daysOfMonth(year, iMonth);
            }
            offset -= days;

            if (leap && iMonth == (leapMonth + 1)) leap = false;
        }

        if (offset <= 0) {
            offset += days;
            iMonth--;
        }

        month = iMonth;
        day = offset;
        //对month，day，leap赋值结束
    }


    //下面四个函数是构造函数的辅助函数
    private static final int daysOfYear(int y) {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) {
            if ((lunarInfo[y - 1900] & i) != 0) sum += 1;
        }
        return (sum + daysOfLeapMonth(y));
    }

    private static final int daysOfLeapMonth(int y) {
        if (monthOfLeap(y) != 0) {
            if ((lunarInfo[y - 1900] & 0x10000) != 0)
                return 30;
            else
                return 29;
        } else
            return 0;
    }

    private static final int monthOfLeap(int y) {
        return (int) (lunarInfo[y - 1900] & 0xf);
    }

    private static final int daysOfMonth(int y, int m) {
        if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
            return 29;
        else
            return 30;
    }


    //根据month和day的值求农历日期（下面两个函数）
    public final String getLunarDate() {
        return (leap ? "闰" : "") + lunarMonth[month - 1] + "月" + getLunarDay(day);
    }

    public static String getLunarDay(int day) {
        String chineseTen[] = {"初", "十", "廿", "三"};
        int n = day % 10 == 0 ? 9 : day % 10 - 1;
        if (day > 30)
            return "";
        if (day == 10)
            return "初十";
        else
            return chineseTen[day / 10] + lunarDay[n];
    }
}