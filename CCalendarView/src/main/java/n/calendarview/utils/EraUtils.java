package n.calendarview.utils;


/**
 * Created by yunshouyi on 15/7/23.
 */
public class EraUtils {
    public static final int INIT_YEAR = 1900;
    public static final String[] HEAVENLY_STEMS = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    public static final String[] EARTHLY_BRANCHES = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    public static final String[] TWELVE_ZODIAC = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

    public static String getYearForHeavenlyStems(int year) {
        int position = (year - INIT_YEAR) % 10;
        return HEAVENLY_STEMS[(position + 6) % 10];
    }


    public static String getYearForEarthlyBranches(int year) {
        int position = (year - INIT_YEAR) % 12;
        return EARTHLY_BRANCHES[position];
    }


    public static String getYearForTwelveZodiac(int year) {
        int position = (year - INIT_YEAR) % 12;
        return TWELVE_ZODIAC[position];
    }
}
