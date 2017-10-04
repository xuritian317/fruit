package com.example.xu.myapplication;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Created by xu on 2017/10/4.
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));

        int MAX_CACHE_SIZE = 100 * 1024 * 1024;
        String CACHE_FILE_NAME = "imgCache";
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, CACHE_FILE_NAME, MAX_CACHE_SIZE));

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String downloadDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + CACHE_FILE_NAME;
            //路径---->sdcard/imgCache builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, MAX_CACHE_SIZE));
        } else {
            //路径---->/sdcard/Android/data/<application package>/cache/imgCache
            builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, CACHE_FILE_NAME, MAX_CACHE_SIZE));
        }

    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        //配置glide网络加载框架
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
