package n.calendarview.utils;


/**
 * 15/7/23
 *
 * @author nanck
 */
public class EraUtils {
    /**
     * 1900 农历庚子年
     */
    private static final int INIT_YEAR = 1900;

    private static final String[] HEAVENLY_STEMS = {"庚", "辛", "壬", "癸", "甲", "乙", "丙", "丁", "戊", "己"};
    private static final String[] EARTHLY_BRANCHES = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    private static final String[] TWELVE_ZODIAC = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

    // FIXME: 2016/9/28 年份小于 1900 时 position 为负数
    public static String getYearForHeavenlyStems(int year) {
        int position = (year - INIT_YEAR) % 10;
        return HEAVENLY_STEMS[position];
    }

    // FIXME: 2016/9/28 年份小于 1900 时 position 为负数
    public static String getYearForEarthlyBranches(int year) {
        int position = (year - INIT_YEAR) % 12;
        return EARTHLY_BRANCHES[position];
    }

    // FIXME: 2016/9/28 年份小于 1900 时 position 为负数
    public static String getYearForTwelveZodiac(int year) {
        int position = (year - INIT_YEAR) % 12;
        return TWELVE_ZODIAC[position];
    }
}
