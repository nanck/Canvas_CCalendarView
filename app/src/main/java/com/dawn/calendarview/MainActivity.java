package com.dawn.calendarview;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import n.calendarview.utils.EraUtils;
import n.calendarview.utils.SolarUtils;
import n.calendarview.widget.CalendarView;


public class MainActivity extends Activity {
    private CalendarView mCalvMain;
    private TextView mTvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalvMain = (CalendarView) findViewById(R.id.calv_main);
        mTvDesc = (TextView) findViewById(R.id.tv_desc);

        final String tg = EraUtils.getYearForHeavenlyStems(mCalvMain.getYear());
        final String dz = EraUtils.getYearForEarthlyBranches(mCalvMain.getYear());
        final String sx = EraUtils.getYearForTwelveZodiac(mCalvMain.getYear());

        mTvDesc.setText(tg + dz + "    " + sx + "年/" + mCalvMain.getYear() + "    " + new SolarUtils(Calendar.getInstance()).getLunarDate());

        mCalvMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mTvDesc.setText(EraUtils.getYearForHeavenlyStems(mCalvMain.getYear())
                            + EraUtils.getYearForEarthlyBranches(mCalvMain.getYear()) + "    " + EraUtils.getYearForTwelveZodiac(mCalvMain.getYear()) + "年/" + mCalvMain.getYear());
                }
                return false;
            }
        });

    }
}
