package com.example.zheng.carouseldemo.Model;

/**
 * Created by zheng on 2018/1/20.
 */

public class SlideInfo {

    int imageId = 0;
    String imageUrl;

    public SlideInfo(int imageId) {
        this.imageId = imageId;
    }

    public SlideInfo(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
