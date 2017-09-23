package com.eyck.opencvdemo.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

/**
 * Created by Eyck on 2017/8/19.
 */

public class ImageLoader {

    private LruCache<String ,Bitmap> mLruCache;
//    private DiskLruCache mDiskLruCache;

    public static Bitmap decodeSampleBitmapFromResource(Resources res,int resId,int reqWidth,int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,resId,options);
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        if(outHeight>reqHeight || outWidth>reqWidth) {
            int halfHeight = outHeight/2;
            int halfWidth = outWidth/2;
            while((halfHeight/inSampleSize) >= reqHeight && (halfWidth/inSampleSize) >=reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private static void lruCache(String key, final Bitmap bitmap){
        int maxMemory= (int) (Runtime.getRuntime().maxMemory()/1024);
        int cacheMemory = maxMemory/8;
        LruCache mMemoryCache;
        mMemoryCache = new LruCache<String,Bitmap>(cacheMemory){

        };

//        LruCache mMemoryCache = new LruCache<String,Bitmap>(cacheMemory){
//            protected int sizeOf(Object key, Bitmap bitmap1) {
//                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
//            }
//        };
    }

}
