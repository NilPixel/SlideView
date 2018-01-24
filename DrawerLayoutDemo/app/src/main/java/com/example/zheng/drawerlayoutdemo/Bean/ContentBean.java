package com.example.zheng.drawerlayoutdemo.Bean;

/**
 * Created by zheng on 2018/1/24.
 */

public class ContentBean {

    private int imageView;
    private String text;
    private int id;

    public ContentBean() {}

    public ContentBean(int imageView, String text, int id) {
        this.imageView = imageView;
        this.text = text;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
