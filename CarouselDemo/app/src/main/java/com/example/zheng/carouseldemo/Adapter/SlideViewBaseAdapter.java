package com.example.zheng.carouseldemo.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng on 2018/1/20.
 */

public abstract class SlideViewBaseAdapter extends PagerAdapter {
    private List<View> views;

    public SlideViewBaseAdapter(){
        views = new ArrayList<>();
    }

    @Override
    public int getCount() {
        /**
         * 这里需要说明一点，因为我们需要循环滑动
         * 但是ViewPager本身并不支持，所以我们需要给
         * ViewPager的页面设置成int最大值造成循环滑动
         * 的假象，具体可以在网上搜索ViewPager循环滑动
         * 可能会有更好的实现方式
         */
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view;
        /**
         * 我在实验的时候发现有时候size和position的差值大于一
         * 造成越界，所以在这添加从size到实际位置的View
         */
        if (views.size() <= (position % getSize())) {
            for(int i=views.size() ; i<=position%getSize();++i){
                views.add(getView(container,i));
            }
        }
        view = views.get(position % getSize());
        if(view.getParent() != null){
            container.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position % getSize()));
    }

    /**
     *抽象的方法，用于获取实际需要显示的View
     */
    public abstract View getView(ViewGroup container, int position);

    public abstract int getSize();

}
