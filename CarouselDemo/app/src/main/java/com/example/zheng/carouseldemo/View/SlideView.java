package com.example.zheng.carouseldemo.View;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zheng.carouseldemo.Adapter.SlideViewBaseAdapter;
import com.example.zheng.carouseldemo.R;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

/**
 * Created by zheng on 2018/1/20.
 */

public class SlideView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private ViewPager viewContainer;        // 图片容器
    private LinearLayout pointContainer;    // 指示点容器

    private SlideViewBaseAdapter adapter;   // 适配器
    private int pointCount;                 // 指示点位置

    private ImageView[] points;             // 放指示点图片的数组
    private int lastPosition;               //上个指示点的位置

    private Timer timer;                    //定时器
    private TimerTask timerTask;            //定时器任务

    private boolean isTouch = false;        // 是否触摸

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            viewContainer.setCurrentItem(viewContainer.getCurrentItem() + 1);
//        }
//    };
    private Handler mhandler = new MyHandler(this);


    static class MyHandler extends Handler {
        WeakReference<SlideView> mSlideView;
        private MyHandler(SlideView slideView){
            mSlideView = new WeakReference<SlideView>(slideView);
        }

        @Override
        public void handleMessage(Message msg) {
            SlideView slideView = mSlideView.get();
            slideView.viewContainer.setCurrentItem(slideView.viewContainer.getCurrentItem() + 1);
        }
    }

    public SlideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        viewContainer = findViewById(R.id.view_container);
        pointContainer = findViewById(R.id.id_point_container);
        viewContainer.addOnPageChangeListener(this);
    }

    public void setData(SlideViewBaseAdapter adapter) {
        this.adapter = adapter;
        pointCount = adapter.getSize();
        viewContainer.setAdapter(this.adapter);
        initPoint();
        /**
         *这样做是可以保证初始情况下可以向左滑动，
         *但是如果有毅力的话，还是能左滑到尽头的
         *这个方法也是在别人的博客里看见的
         */
        viewContainer.setCurrentItem(pointCount * 100);
        startAutoSlide();
    }

    private void initPoint() {
        if (pointCount == 0) {
            return;
        }
        points = new ImageView[pointCount];

        for (int i = 0; i < pointCount; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.mipmap.banner_off);
            pointContainer.addView(imageView);
            points[i] = imageView;
        }
        if (points[0] != null) {
            points[0].setImageResource(R.mipmap.banner_on);
        }

        lastPosition = 0;
    }

    private void changePoint(int currentPosition) {
        if (lastPosition == currentPosition) {
            return;
        }
        points[currentPosition].setImageResource(R.mipmap.banner_on);
        points[lastPosition].setImageResource(R.mipmap.banner_off);
        lastPosition = currentPosition;
    }

    private void startAutoSlide() {
        if (timer == null) {
            timer = new Timer();
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }

        timerTask = new TimerTask() {
            @Override
            public void run() {
                //如果有触摸事件，则取消定时器任务
                if (isTouch) {
                    return;
                }
                mhandler.sendEmptyMessage(0x00);
            }
        };
        timer.schedule(timerTask, 300, 3000);
    }

    public void cancelAutoSlide() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changePoint(position % pointCount);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     *isTouch变量用于是否用户在点击或者滑动
     *可根据这个标记来暂时停止自动滑动
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
