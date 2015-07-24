package n.calendarview.utils;

import java.util.Calendar;

/**
 * Created by nanck on 15/7/16.
 */
public class CalendarUtils {
    public static int getMonthDayCount(int month, int year) {
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                return 31;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return 30;
            case Calendar.FEBRUARY:
                return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Didn't find this month");
        }

    }


    public static int getDayForWeekInMonth(int date, Calendar calendar) {
        if (date < 1 || date > 31) {
            return 1;
        }
        calendar.set(Calendar.DAY_OF_MONTH, date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

}
