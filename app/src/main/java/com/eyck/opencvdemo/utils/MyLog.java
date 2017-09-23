package com.eyck.opencvdemo.utils;

import android.util.Log;

/**
 * Created by Eyck on 2017/8/27.
 */

public class MyLog {
    private static final String TAG = "OPENCV";
    private static final boolean ISDEBUG = true;

    public static void d(String msg){
        if(ISDEBUG) {
            Log.d(TAG,msg);
        }
    }

    public static void e(String msg){
        if(ISDEBUG) {
            Log.e(TAG,msg);
        }
    }

    public static void w(String msg){
        if(ISDEBUG) {
            Log.w(TAG,msg);
        }
    }


}
