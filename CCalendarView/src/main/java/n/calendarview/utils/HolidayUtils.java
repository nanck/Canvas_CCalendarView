package n.calendarview.utils;


import java.util.Calendar;

import n.calendarview.widget.CalendarView;

/**
 * Created by yunshouyi on 15/7/23.
 */
public class HolidayUtils {
    private static Calendar calendar = Calendar.getInstance();
    public static boolean isHoliday = true;

    public static String getCalendarHoliday(int day, int month) {
        switch (day) {
            case 1:
                isHoliday = true;
                if (month == Calendar.JANUARY) {
                    return "元旦";
                }
                if (month == Calendar.APRIL) {
                    return "愚人节";
                }
                if (month == Calendar.MAY) {
                    return "劳动节";
                }
                if (month == Calendar.JUNE) {
                    return "儿童节";
                }
                if (month == Calendar.JULY) {
                    return "建党节";
                }
                if (month == Calendar.AUGUST) {
                    return "建军节";
                }
                if (month == Calendar.OCTOBER) {
                    return "国庆节";
                }
            default:
                isHoliday = false;
                switch (month) {
                    case Calendar.FEBRUARY:
                        if (day == 2) {
                            return "湿地日";
                        }
                        if (day == 14) {
                            return "情人节";
                        }
                        break;
                    case Calendar.MARCH:
                        if (day == 8) {
                            return "妇女节";
                        }
                        if (day == 12) {
                            return "植树节";
                        }
                        if (day == 15) {
                            return "消权日";
                        }
                        break;
                    case Calendar.APRIL:
                        if (day == 22) {
                            return "地球日";
                        }
                        break;

                    case Calendar.MAY:
                        if (day == 4) {
                            return "青年节";
                        }
                        if (day == 12) {
                            return "护士节";
                        }
                        if (day == 15) {
                            return "博物馆日";
                        } else {
                            return calculateHolidayForWeek(day, month);
                        }

                    case Calendar.JUNE:
                        if (day == 5) {
                            return "环境日";
                        }
                        if (day == 23) {
                            return "奥林匹克日";
                        } else {
                            return calculateHolidayForWeek(day, month);
                        }
                    case Calendar.JULY:
                        break;
                    case Calendar.AUGUST:
                        break;
                    case Calendar.SEPTEMBER:
                        if (day == 3) {
                            return "抗战胜利日";
                        }
                        break;
                    case Calendar.OCTOBER:
                        break;
                    case Calendar.NOVEMBER:
                        break;
                    case Calendar.DECEMBER:
                        if (day == 1) {
                            return "艾滋病日";
                        }
                        if (day == 25) {
                            return "圣诞节";
                        }
                        break;
                }
                isHoliday = false;
                String text = new SolarUtils(CalendarView.mCalendar).getLunarDate();
                String date = text.length() == 4 ? text.substring(2) : text.substring(3);
                return date.equals("初一") ? text.substring(0, 2) : date;
        }
    }

    private static String holiday = "";

    public static String calculateHolidayForWeek(int day, int month) {
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        if (CalendarUtils.getDayForWeekInMonth(day, calendar) == 0) {
            switch (month) {
                case Calendar.MAY:
                    if (calendar.get(Calendar.WEEK_OF_MONTH) == 3) {
                        holiday = "母亲节";
                    }
                    break;
                case Calendar.JUNE:
                    if (calendar.get(Calendar.WEEK_OF_MONTH) == 4) {
                        holiday = "父亲节";
                    }
                    break;
                default:
                    break;
            }
        } else {
            String text = new SolarUtils(CalendarView.mCalendar).getLunarDate();
            String date = text.length() == 4 ? text.substring(2) : text.substring(3);
            holiday = date.equals("初一") ? text.substring(0, 2) : date;
        }
        return holiday;
    }


}
