package com.veer.rx.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * 图片加载类
 * 防止更换框架
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/8/10
 */

public class ImageLoader {
    private static ImageLoader mInstance;

    public static ImageLoader getInstance(){
        if(mInstance == null){
            mInstance = new ImageLoader();
        }
        return mInstance;
    }

    /**
     * 显示图片
     * @param context
     * @param url
     * @param view
     */
    public void displayImage(Context context, String url, ImageView view) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(view);
    }
    public void displayImage(Context context, String url, ImageView view,int defaultViewId,int errorViewId) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop()
                .placeholder(defaultViewId)
                .error(errorViewId);
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(view);
    }

}
