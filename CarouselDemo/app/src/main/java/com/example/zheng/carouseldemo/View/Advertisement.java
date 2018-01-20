package com.example.zheng.carouseldemo.View;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

import com.example.zheng.carouseldemo.R;

/**
 * Created by zheng on 2018/1/20.
 */

public class Advertisement implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private Context context;
    private LayoutInflater inflater;

    private boolean fitXY;
    private int timeDuration;// pager切换时长
    private boolean isPlay = true;

    List<View> views;

    //底部小圆点
    private ImageView[] dots;

    //记录当前选中位置
    private int currentPosition;

    Timer timer;
    TimerTask timerTask;
    int count = 0;

    public Advertisement(Context context, boolean fitXY, LayoutInflater inflater, int timeDuration, boolean isPlay) {
        this.context = context;
        this.fitXY = fitXY;
        this.inflater = inflater;
        this.timeDuration = timeDuration;
        this.isPlay = isPlay;
    }

    private Handler runHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x01:
                    int currentPage = (Integer)msg.obj;
                    setCurrentDot(currentPage);
                    viewPager.setCurrentItem(currentPage);
                    break;
            }
        }

    };

    private void setCurrentDot(int position) {
        if (position < 0 || position > views.size() - 1 || currentPosition == position) {
            return;
        }

        dots[position].setEnabled(false);
        dots[currentPosition].setEnabled(true);

        currentPosition = position;
    }

    public View initView(final List advertiseArray) {
        View view = inflater.inflate(R.layout.advertisement_board, null);
        viewPager = view.findViewById(R.id.id_advertise_viewpager);
        viewPager.setOnPageChangeListener(this);
        views = new ArrayList<View>();
        return view;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }
}
