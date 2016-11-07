package lx.base.apphall.popwindow;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

import lx.base.apphall.R;

/**
 * 创建时间 2016/10/19
 * Created by linxiao.
 */

public class PicassoImageLoader implements ImageLoader{
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)//
                .load(new File(path))//
                .placeholder(R.mipmap.default_image)//
                .error(R.mipmap.default_image)//
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
