package com.gmonetix.truthdare.utils;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * @author Gmonetix
 */

public class Helper {

    public static void getUilInstance(Context context){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static String getFbImageLink(String userId, String dimensionPX) {
        return "https://graph.facebook.com/" + userId + "/picture?width="+dimensionPX+"&height="+dimensionPX;
    }

}
