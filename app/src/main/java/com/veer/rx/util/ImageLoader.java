package com.veer.rx.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 图片加载类
 * 防止更换框架
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/8/10
 */

public class ImageLoader {

    public static void load(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(view);
    }

}
