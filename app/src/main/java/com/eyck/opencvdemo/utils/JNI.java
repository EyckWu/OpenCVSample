package com.eyck.opencvdemo.utils;

/**
 * Created by Eyck on 2017/8/19.
 */

public class JNI {
    public static native void getImagePro(int[] pixels, int w, int h);


    /**
     * 图像灰度处理
     * @param pixels
     * @param w
     * @param h
     * @return
     */
    public static native int[] grayProc(int[] pixels, int w, int h);

    public static native void grayProcs(int[] pixels, int w, int h);

    /**
     * 图像平滑处理
     * @param pixels
     * @param w
     * @param h
     * @return
     */
    public static native void blurProc(int[] pixels, int w, int h);

    /**
     * 图像边缘
     * @param pixels
     * @param w
     * @param h
     * @return
     */
    public static native void erodeProc(int[] pixels, int w, int h);


    public static native void remapProc(int[] pixels, int w, int h);

    public static native void translateProc(int[] pixels, int w, int h,int xOffSet, int yOffSet);

    public static native void translateFullProc(int[] pixels, int w, int h,int xOffSet, int yOffSet);

    public static native void scaleProc(int[] pixels, int w, int h,int kx, int ky);

    public static native void cannyProc(int[] pixels, int width, int height) ;
}
