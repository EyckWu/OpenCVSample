package com.eyck.opencvdemo.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Eyck on 2017/8/16.
 */

public class JavaImageUtils {

    static {
        System.loadLibrary("native-lib");
    }

    //Java亮度+30
    public static Bitmap getImageProcess(Bitmap bitmap){
        long startTime=System.currentTimeMillis();
        /***算法***/
        //确定图片大小
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        //创建新的图片
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //操作像素点
        int ld = 30;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                int color = bitmap.getPixel(i, j);
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);

//                int rgb = Color.rgb(0, (int) (green + (ld*0.587)), (int) (blue + (ld*0.114)));
                int rgb = Color.rgb(red+ld, green+ld, blue+ld);

                newBitmap.setPixel(i,j,rgb);
            }
        }

        long endTime=System.currentTimeMillis();
        MyLog.d("getImageProcess:"+(endTime-startTime));
        return newBitmap;
    }

    /**
     * 修改图像RGB
     * @param bitmap
     * @return
     */
    public static Bitmap getImageNDK(Bitmap bitmap){
        long startTime=System.currentTimeMillis();
        //确定图片大小
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();

        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
//        int[] resultInt = JNI.getImagePro(pixels, width, height);
        JNI.getImagePro(pixels,width,height);
        Bitmap resultImg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(pixels, 0, width, 0, 0, width, height);
        return resultImg;
    }

    /**
     * NDK灰度
     * @param bitmap
     * @return
     */
    public static Bitmap grayProcessNDK(Bitmap bitmap){
        long startTime=System.currentTimeMillis();
        //确定图片大小
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        //创建新的图片
//        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        int w = bmp.getWidth();
//        int h = bmp.getHeight();

        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        JNI.grayProcs(pixels, width, height);
        Bitmap resultImg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(pixels, 0, width, 0, 0, width, height);
        long endTime=System.currentTimeMillis();
        MyLog.d("grayProcessNDK:"+(endTime-startTime));
        return resultImg;
    }


    /**
     * Java灰度
     * @param bitmap
     * @return
     */
    public static Bitmap grayProcessJava(Bitmap bitmap){
        long startTime=System.currentTimeMillis();
        //确定图片大小
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        //创建新的图片
        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //操作像素点
        int ld = 8;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                int color = bitmap.getPixel(i, j);
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);

                int gray = (int) (red*0.299+green*0.587+blue*0.114);

//                int rgb = Color.rgb(0, (int) (green + (ld*0.587)), (int) (blue + (ld*0.114)));
                int rgb = Color.rgb(gray, gray, gray);

                newBitmap.setPixel(i,j,rgb);
            }
        }
//        long endTime=System.currentTimeMillis();
//        Log.d("EYCK","grayProcessJava:"+(endTime-startTime));
//        System.out.println("grayProcessJava:"+(endTime-startTime));
        return newBitmap;
    }

    /**
     * NDK平滑
     * @param bitmap
     * @return
     */
    public static Bitmap blurProcessNDK(Bitmap bitmap){
        //确定图片大小
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        //创建新的图片
//        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        int w = bmp.getWidth();
//        int h = bmp.getHeight();

        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        JNI.blurProc(pixels, width, height);

        Bitmap resultImg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(pixels, 0, width, 0, 0, width, height);
        return resultImg;
    }

    /**
     * NDK腐蚀
     * @param bitmap
     * @return
     */
    public static Bitmap erodeProcessNDK(Bitmap bitmap){
        long startTime=System.currentTimeMillis();
        //确定图片大小
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        //创建新的图片
//        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        int w = bmp.getWidth();
//        int h = bmp.getHeight();

        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        JNI.erodeProc(pixels, width, height);

        Bitmap resultImg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(pixels, 0, width, 0, 0, width, height);
        long endTime=System.currentTimeMillis();
        MyLog.d("candyProcessNDK:"+(endTime-startTime));
        return resultImg;
    }

    /**
     * NDK腐蚀
     * @param bitmap
     * @return
     */
    public static Bitmap remapProcessNDK(Bitmap bitmap){
        long startTime=System.currentTimeMillis();
        //确定图片大小
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        //创建新的图片

        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        JNI.remapProc(pixels, width, height);

        Bitmap resultImg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(pixels, 0, width, 0, 0, width, height);
        long endTime=System.currentTimeMillis();
        MyLog.d("candyProcessNDK:"+(endTime-startTime));
        return resultImg;
    }

    /**
     * 平移1
     * @param bitmap
     * @return
     */
    public static Bitmap translateProcessNDK(Bitmap bitmap){
        long startTime=System.currentTimeMillis();
        //确定图片大小
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        //创建新的图片

        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        JNI.translateProc(pixels, width, height,width/4,height/4);

        Bitmap resultImg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(pixels, 0, width, 0, 0, width, height);
        long endTime=System.currentTimeMillis();
        MyLog.d("candyProcessNDK:"+(endTime-startTime));
        return resultImg;
    }

    /**
     * 平移2
     * @param bitmap
     * @return
     */
    public static Bitmap translateFullProcessNDK(Bitmap bitmap){
        //确定图片大小
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        //创建新的图片

        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        JNI.translateFullProc(pixels, width, height,width/4,height/4);

        Bitmap resultImg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(pixels, 0, width, 0, 0, width, height);
        return resultImg;
    }

    /**
     * 缩放
     * @param bitmap
     * @return
     */
    public static Bitmap scaleProcessNDK(Bitmap bitmap){
        //确定图片大小
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        //创建新的图片

        int[] pixels = new int[width*height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        JNI.scaleProc(pixels, width, height,width/4,height/4);

        Bitmap resultImg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(pixels, 0, width, 0, 0, width, height);
        return resultImg;
    }

    public static Bitmap cannyProcessNDK(Bitmap mBitmap) {
        long startTime=System.currentTimeMillis();
        //确定图片大小
        int height = mBitmap.getHeight();
        int width = mBitmap.getWidth();
        //创建新的图片

        int[] pixels = new int[width*height];
        mBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        JNI.cannyProc(pixels, width, height);

        Bitmap resultImg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(pixels, 0, width, 0, 0, width, height);
        long endTime=System.currentTimeMillis();
        MyLog.d("cannyProcessNDK:"+(endTime-startTime));
        return resultImg;
    }
}

