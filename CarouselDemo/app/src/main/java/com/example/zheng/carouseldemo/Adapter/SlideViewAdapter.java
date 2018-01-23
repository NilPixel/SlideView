package com.example.zheng.carouseldemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zheng.carouseldemo.Model.SlideInfo;
import com.example.zheng.carouseldemo.R;

import java.util.List;

/**
 * Created by zheng on 2018/1/20.
 */

public class SlideViewAdapter extends SlideViewBaseAdapter {
    List<SlideInfo> slideInfos;

    Context context;

    public SlideViewAdapter(Context context, List<SlideInfo> slideInfos){
        this.context = context;
        this.slideInfos = slideInfos;
    }

    /**
     * 实现了获取View的方法
     * 使用了ImageLoder来获取图片显示到ImageView上
     */
    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView;
        View view = LayoutInflater.from(context).inflate(R.layout.slide_item, null);
        imageView = view.findViewById(R.id.id_image);
        imageView.setImageResource(slideInfos.get(position).getImageId());
        return view;
    }

    @Override
    public int getSize() {
        return slideInfos.size();
    }
}
