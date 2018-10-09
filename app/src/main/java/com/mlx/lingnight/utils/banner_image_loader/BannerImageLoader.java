package com.mlx.lingnight.utils.banner_image_loader;

import android.content.Context;
import android.widget.ImageView;

import com.mlx.lingnight.utils.GlideUtils;
import com.youth.banner.loader.ImageLoader;

public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideUtils.loadImageView(context,path,imageView);
    }
}
