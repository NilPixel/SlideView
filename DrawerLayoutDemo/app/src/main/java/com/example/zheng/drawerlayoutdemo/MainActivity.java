package com.example.zheng.drawerlayoutdemo;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

import com.example.zheng.drawerlayoutdemo.Adapter.ContentAdapter;
import com.example.zheng.drawerlayoutdemo.Bean.ContentBean;
import com.example.zheng.drawerlayoutdemo.Fragment.NewsFragment;
import com.example.zheng.drawerlayoutdemo.Fragment.SubscriptionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zheng on 2018/1/24.
 */

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RelativeLayout rightLayout;
    private List<ContentBean> list;

    private ContentAdapter contentAdapter;
    private ImageView leftMenu, rightMenu;
    private ListView listView;

    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sideslip_layout);
        setContentView(R.layout.activity_main);
//        getActionBar().hide();

        leftMenu = findViewById(R.id.id_left_menu);
        rightMenu = findViewById(R.id.id_right_menu);

        drawerLayout = findViewById(R.id.id_drawerlayout);
        rightLayout = findViewById(R.id.id_right_list);
        listView = findViewById(R.id.id_listview_left);
        fragmentManager = getSupportFragmentManager();

        initData();

        contentAdapter = new ContentAdapter(this, list);
        listView.setAdapter(contentAdapter);

        /**
         * 展开左边菜单
         */
        //添加各种点击事件（按钮和列表）
        leftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        /**
         * 收起左边菜单
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                android.support.v4.app.FragmentTransaction bt = fragmentManager.beginTransaction();
                switch ((int) id) {
                    case 1:
                        bt.replace(R.id.id_content, new NewsFragment());
                        break;
                    case 2:
                        bt.replace(R.id.id_content, new SubscriptionFragment());

                    default:
                        break;
                }
                bt.commit();
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        /**
         * 展开右边菜单
         */
        rightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        /**
         * 收起右边菜单
         */
        rightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

    }

    private void initData() {
        list = new ArrayList<ContentBean>();
        list.add(new ContentBean(R.drawable.doctoradvice2, "新闻",1));
        list.add(new ContentBean(R.drawable.infusion_selected, "订阅",2));
        list.add(new ContentBean(R.drawable.mypatient_selected, "图片",3));
        list.add(new ContentBean(R.drawable.mywork_selected, "视频",4));
        list.add(new ContentBean(R.drawable.nursingcareplan2, "跟帖",5));
        list.add(new ContentBean(R.drawable.personal_selected, "投票",6));
    }
}
