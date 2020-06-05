package com.example.zheng.carouseldemo.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zheng.carouseldemo.Adapter.SlideViewAdapter;
import com.example.zheng.carouseldemo.Model.SlideInfo;
import com.example.zheng.carouseldemo.R;
import com.example.zheng.carouseldemo.View.SlideView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng on 2018/1/18.
 */

public class MainActivity extends AppCompatActivity {

    SlideView slideView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoader.getInstance().
                init(ImageLoaderConfiguration.createDefault(this));
        slideView = (SlideView)LayoutInflater.from(this).inflate(R.layout.slide_view, null);

        List<SlideInfo> slideInfos = new ArrayList<>();
        slideInfos.add(new SlideInfo(R.mipmap.a));
        slideInfos.add(new SlideInfo(R.mipmap.b));
        slideInfos.add(new SlideInfo(R.mipmap.c));
        slideInfos.add(new SlideInfo(R.mipmap.d));
        slideInfos.add(new SlideInfo(R.mipmap.e));
        slideInfos.add(new SlideInfo(R.mipmap.f));
        slideInfos.add(new SlideInfo(R.mipmap.g));

        List<SlideInfo> slideInfosByUrl = new ArrayList<>();
        slideInfosByUrl.add(new SlideInfo("https://image4.cn/uimg/cms/img/151660641005425483.jpg"));
        slideInfosByUrl.add(new SlideInfo("https://image.cn/uimg/aps/material/151662458452762361.jpg"));
        slideInfosByUrl.add(new SlideInfo("https://image.cn/uimg/aps/material/151661433976544381.jpg"));
        slideInfosByUrl.add(new SlideInfo("https://image.cn/uimg/aps/material/151660847439506272.jpg"));
        slideInfosByUrl.add(new SlideInfo("https://image.cn/uimg/aps/material/151634901535132616.jpg"));
        slideInfosByUrl.add(new SlideInfo("https://image.cn/uimg/aps/material/151564086165733425.jpg"));
        slideInfosByUrl.add(new SlideInfo("https://image1.cn/uimg/cms/img/151661113545922568.jpg"));
        slideView.setData(new SlideViewAdapter(this, slideInfosByUrl));

        addContentView(slideView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
