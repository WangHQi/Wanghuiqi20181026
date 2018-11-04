package wanghuiqi.bawie.com.wanghuiqi20181026.activity.activity.utils;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyAppImage extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //全局配置
        ImageLoaderConfiguration builder = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(builder);
    }
}
