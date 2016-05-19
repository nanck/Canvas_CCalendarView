package n.calendarview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import java.util.Calendar;

import n.calendarview.utils.CalendarUtils;
import n.calendarview.utils.HolidayUtils;
import n.ccalendarviewlibrary.R;

/**
 * @author nanck  15/7/16.
 */
public class CalendarView extends View {
    private static final String LOG_TAG = "CalendarView";

    private TextPaint mDateNumPaint;
    private TextPaint mWeekNumPaint;
    private TextPaint mNumSubTextPaint;
    private TextPaint mNumSubHolidayPaint;
    private TextPaint mMonthTitlePaint;

    private Paint mDateBGPaint;
    private Paint mWeekBGPaint;

    private RectF mTitleBGRectF;
    private Paint mCurrentDateBGPaint;
    private RectF mDateBGREctF;

    public static Calendar mCalendar = Calendar.getInstance();

    private final String[] mWeeks = {"日", "一", "二", "三", "四", "五", "六"};

    public CalendarView(Context context) {
        super(context);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CalendarView);
        int dayTextColor = array.getColor(R.styleable.CalendarView_day_textColor, 0xCC00FFFF);
        float dayTextSize = array.getDimension(R.styleable.CalendarView_day_textSize, 55.0f);

        mDateNumPaint.setColor(dayTextColor);
        mDateNumPaint.setTextSize(dayTextSize);

        array.recycle();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();
        initRect();

    }

    private void initPaint() {
        mDateNumPaint = new TextPaint();
        mDateNumPaint.setAntiAlias(true);   // 打开抗锯齿
        mDateNumPaint.setTextAlign(Paint.Align.CENTER); // 居中

        mNumSubTextPaint = new TextPaint(mDateNumPaint);
        mNumSubTextPaint.setTextSize(27.0f);
        mNumSubTextPaint.setColor(Color.parseColor("#CCFFFFFF"));

        mNumSubHolidayPaint = new TextPaint(mNumSubTextPaint);
        mNumSubHolidayPaint.setColor(Color.parseColor("#CCFF3333"));


        mMonthTitlePaint = new TextPaint(mDateNumPaint);
        mMonthTitlePaint.setTextSize(87.0f);
        mMonthTitlePaint.setColor(Color.parseColor("#CC93EBFD"));

        mDateBGPaint = new Paint();
        mDateBGPaint.setColor(Color.parseColor("#994A4A4A"));
        mDateBGPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mDateBGPaint.setStrokeJoin(Paint.Join.ROUND);
        mDateBGPaint.setDither(true);
        mDateBGPaint.setStrokeWidth(3);
        mDateBGPaint.setStrokeCap(Paint.Cap.BUTT);

        mWeekBGPaint = new Paint();
        mWeekBGPaint.setColor(Color.parseColor("#CCFF4A4A"));

        mWeekNumPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mWeekNumPaint.setTextAlign(Paint.Align.CENTER);
        mWeekNumPaint.setColor(Color.parseColor("#CCFFFFFF"));
        mWeekNumPaint.setTextSize(45.0f);

        mCurrentDateBGPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCurrentDateBGPaint.setDither(true);
        mCurrentDateBGPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mCurrentDateBGPaint.setColor(Color.parseColor("#CC7AAA24"));
        mCurrentDateBGPaint.setStrokeWidth(3.0f);
        mWeekTextWidth = (int) mWeekNumPaint.measureText(mWeeks[0]);


    }

    private void initRect() {
        mTitleBGRectF = new RectF(0, 0, 1280, 240);
        mDateBGREctF = new RectF(0, 240, 1280, 1120);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (modeWidth == MeasureSpec.EXACTLY) {
            width = sizeWidth;
        } else {
            width = (int) (mDateBGREctF.right);
            if (modeWidth == MeasureSpec.AT_MOST) {
                width = Math.min(width, sizeWidth);
            }
        }

        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (modeHeight == MeasureSpec.EXACTLY) {
            height = sizeHeight;
        } else {
            height = (int) (mDateBGREctF.bottom);
            if (modeHeight == MeasureSpec.AT_MOST) {
                height = Math.min(height, sizeHeight);
            }
        }
        setMeasuredDimension(width, height);
    }


    private int mWeekTextWidth = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(mTitleBGRectF, mWeekBGPaint);
        canvas.drawRect(mDateBGREctF, mDateBGPaint);
        int space = (getWidth() / 7);
        int black = CalendarUtils.getDayForWeekInMonth(1, mCalendar);
        int x = 0;
        int y = 340;

        canvas.drawText((mCalendar.get(Calendar.MONTH) + 1) + "月", 118, 105, mMonthTitlePaint);

        int temp = space / 3;
        for (int i = 0, length = mWeeks.length; i < length; i++) {
            canvas.drawText(mWeeks[i], (((i + 1) * space - temp) - (mWeekTextWidth / 2)), 195, mWeekNumPaint)
            ;
        }

        x = black * space - 70;
        for (int i = 1; i <= CalendarUtils.getMonthDayCount(mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.YEAR)); i++) {
            x += space;
            if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == i && mCalendar.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)) {
                canvas.drawCircle(x, y - 14.5f, 60f, mCurrentDateBGPaint);
            }
            mCalendar.set(Calendar.DAY_OF_MONTH, i);
            canvas.drawText("" + i, x, y, mDateNumPaint);
            if (!(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == i && mCalendar.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH))) {

                canvas.drawText(HolidayUtils.getCalendarHoliday(i, mCalendar.get(Calendar.MONTH)), x, y + 45, mNumSubTextPaint);

            }
            if ((i + black) % 7 == 0) {
                y += 140;
                x = -70;
            }
        }
    }


    public void setYear(int year) {
        year = year < 1990 ? 1990 : year;
        year = year > 2200 ? 2200 : year;
        mCalendar.set(Calendar.YEAR, year);
    }

    public int getYear() {
        return mCalendar.get(Calendar.YEAR);
    }

    public void setMonth(int month) {
        month--;
        month = month < Calendar.JANUARY ? Calendar.JANUARY : month;
        month = month > Calendar.DECEMBER ? Calendar.DECEMBER : month;
        mCalendar.set(Calendar.MONTH, month);
    }

    public int getMonth() {
        return mCalendar.get(Calendar.MONTH) + 1;
    }

    private float x = 0f;
    private float y = 0f;
//    NameValuePair n = new NameValuePair() {
//        @Override
//        public String getName() {
//            return null;
//        }
//
//        @Override
//        public String getValue() {
//            return null;
//        }
//    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (y <= mDateBGREctF.bottom && y >= mTitleBGRectF.bottom) {
                    if (event.getX() > x) {
                        mCalendar.add(Calendar.MONTH, -1);
                        invalidate();
                    } else if (event.getX() < (x + (getWidth() / 2))) {
                        mCalendar.add(Calendar.MONTH, 1);
                        invalidate();
                    }
                }
                Log.e(LOG_TAG, "calendar info : " + getYear() + "-" + getMonth() + "-" + mCalendar.get(Calendar.DAY_OF_MONTH));
                Log.e(LOG_TAG, "calendar info2 : " + mCalendar.get(Calendar.YEAR) + "-" + mCalendar.get(Calendar.MONTH) + "-" + mCalendar.get(Calendar.DAY_OF_MONTH));

                break;
        }
        return true;
    }
}
