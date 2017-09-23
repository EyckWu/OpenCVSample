#include <jni.h>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include <opencv2/opencv.hpp>
#include <opencv2/core/core.hpp>
#include <opencv/cv.h>
#include <opencv/cv.hpp>

using namespace std;
using namespace cv;

extern "C"{
    jstring Java_com_eyck_opencvdemo_MainActivity_stringFromJNI(
            JNIEnv *env, jobject /* this */) {
        std::string hello = "Hello from C++";
        return env->NewStringUTF(hello.c_str());
    }

    /**
     * 改变图像RGB值
     */
    JNIEXPORT void JNICALL Java_com_eyck_opencvdemo_utils_JNI_getImagePro(JNIEnv *env, jobject instance,
                                                                                 jintArray buf, jint w, jint h) {

        jint *cbuf;
        jboolean ptfalse = false;
        cbuf = env->GetIntArrayElements(buf, &ptfalse);
//        env->GetStringUTFChars()
        if(cbuf == NULL){
            return ;
        }

        Mat imgData(h, w, CV_8UC3, (unsigned char*)cbuf);

        uchar* ptr = imgData.ptr(0);
        for(int i = 0; i < w*h; i ++){
            ptr[4*i+1] = ptr[4*i+1]+30;
            ptr[4*i+2] = ptr[4*i+2]+30;
            ptr[4*i+0] = ptr[4*i+0]+30;
        }

        int size=w * h;
        env->SetIntArrayRegion(buf, 0, size, cbuf);
    }

    /**
     * 生成灰白图像矩阵
     */
    JNIEXPORT jintArray JNICALL Java_com_eyck_opencvdemo_utils_JNI_grayProc(JNIEnv *env, jobject instance,
                                                                                    jintArray buf, jint w, jint h) {

        jint *cbuf;
        jboolean ptfalse = false;
        cbuf = env->GetIntArrayElements(buf, &ptfalse);
        if(cbuf == NULL){
            return 0;
        }

        Mat imgData(h, w, CV_8UC3, (unsigned char*)cbuf);

        uchar* ptr = imgData.ptr(0);
        for(int i = 0; i < w*h; i ++){
            uchar grayScale = (uchar)(ptr[4*i+2]*0.299 + ptr[4*i+1]*0.587 + ptr[4*i+0]*0.114);
            ptr[4*i+1] = grayScale;
            ptr[4*i+2] = grayScale;
            ptr[4*i+0] = grayScale;
        }

        jint* outImage=new jint[w*h];
        for(int i=0;i<w*h;i++) {
            outImage[i]=cbuf[i];
        }

        int size=w * h;
        jintArray result = env->NewIntArray(size);
        env->SetIntArrayRegion(result, 0, size, outImage);
        env->ReleaseIntArrayElements(buf, cbuf, 0);
        return result;
    }

    /**
     * 生成灰白图像矩阵
     */
    JNIEXPORT void JNICALL Java_com_eyck_opencvdemo_utils_JNI_grayProcs(JNIEnv *env, jobject instance,
                                                                                    jintArray buf, jint w, jint h) {

        jint *cbuf;
        jboolean ptfalse = false;
        cbuf = env->GetIntArrayElements(buf, &ptfalse);
        if(cbuf == NULL){
            return ;
        }

        Mat imgData(h, w, CV_8UC3, (unsigned char*)cbuf);

        uchar* ptr = imgData.ptr(0);
        for(int i = 0; i < w*h; i ++){
            uchar grayScale = (uchar)(ptr[4*i+2]*0.299 + ptr[4*i+1]*0.587 + ptr[4*i+0]*0.114);
            ptr[4*i+1] = grayScale;
            ptr[4*i+2] = grayScale;
            ptr[4*i+0] = grayScale;
        }


        int size=w * h;
        env->SetIntArrayRegion(buf, 0, size, cbuf);
    }

    /**
     * 平滑图像
     */
    JNIEXPORT void JNICALL Java_com_eyck_opencvdemo_utils_JNI_blurProc(JNIEnv *env, jobject instance,
                                                                                 jintArray buf, jint w, jint h) {

        jint *cbuf;
        jboolean ptfalse = false;
        cbuf = env->GetIntArrayElements(buf, &ptfalse);
        if(cbuf == NULL){
            return ;
        }

        Mat imgData(h, w, CV_8UC4, (unsigned char*)cbuf);

        Mat srcGray(imgData.size(),imgData.type());
        cvtColor(imgData,srcGray,CV_BGR2GRAY);


        Mat blurGray(imgData.size(),imgData.type());
        blur(imgData,blurGray,Size(3,3),Point(-1,-1));

        jint* ptr = (jint *) blurGray.ptr(0);

        int size=w * h;
//        jintArray result = env->NewIntArray(size);
        env->SetIntArrayRegion(buf, 0, size, ptr);
        env->ReleaseIntArrayElements(buf, cbuf, 0);
//        return result;
    }

    /**
     * 腐蚀图像
     */
    JNIEXPORT void JNICALL Java_com_eyck_opencvdemo_utils_JNI_erodeProc(JNIEnv *env, jobject instance,
                                                                                 jintArray buf, jint w, jint h) {

        jint *cbuf;
        cbuf = env->GetIntArrayElements(buf, false);
        if (cbuf == NULL) {
              return ;
        }

        Mat srcImage(h, w, CV_8UC4, (unsigned char*) cbuf);
        //腐蚀算法
        Mat element = getStructuringElement(MORPH_RECT, Size(3, 3));
        Mat dstImage;
        erode(srcImage, dstImage, element);

        jint* ptr = (jint*)dstImage.ptr(0);


        int size = w * h;
//        jintArray result = env->NewIntArray(size);
        env->SetIntArrayRegion(buf, 0, size, ptr);
        env->ReleaseIntArrayElements(buf, cbuf, 0);
//        return result;
    }

    /**
     * 重映射
     */
    JNIEXPORT void JNICALL Java_com_eyck_opencvdemo_utils_JNI_remapProc(JNIEnv *env, jobject instance,
                                                                       jintArray buf, jint w, jint h) {

        jint *cbuf;
        cbuf = env->GetIntArrayElements(buf, false);
        if (cbuf == NULL) {
            return ;
        }

        Mat srcImage(h, w, CV_8UC4, (unsigned char*) cbuf);
        //输出矩阵
        cv::Mat resultImage(srcImage.size(),srcImage.type());

        cv::Mat xMap(srcImage.size(),CV_32FC1);
        cv::Mat yMap(srcImage.size(),CV_32FC1);

        int rows = srcImage.rows;
        int cols = srcImage.cols;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                xMap.at<float>(i,j) = cols-j;
                yMap.at<float>(i,j) = rows-i;
            }
        }

        remap(srcImage,resultImage,xMap,yMap,CV_INTER_LINEAR,cv::BORDER_CONSTANT,cv::Scalar(0,0,0));


        jint* ptr = (jint*)resultImage.ptr(0);


        int size = w * h;
//        jintArray result = env->NewIntArray(size);
        env->SetIntArrayRegion(buf, 0, size, ptr);
        env->ReleaseIntArrayElements(buf, cbuf, 0);
//        return result;
    }
    /**
     * 平移1
     */
    JNIEXPORT void JNICALL Java_com_eyck_opencvdemo_utils_JNI_translateProc(JNIEnv *env, jobject instance,
                                                                       jintArray buf, jint w, jint h, jint offSetX, jint offSetY) {

        jint *cbuf;
        cbuf = env->GetIntArrayElements(buf, false);
        if (cbuf == NULL) {
            return ;
        }

        Mat srcImage(h, w, CV_8UC4, (unsigned char*) cbuf);
        //输出矩阵
        cv::Mat resultImage(srcImage.size(),srcImage.type());

        int rows = srcImage.rows;
        int cols = srcImage.cols;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int x = j-offSetX;
                int y = i-offSetY;
                //边界判断 ptr<cv::Vec3b>(y)获取第y行的首地址
                if (x>=0 && y>=0 && x<cols && y<rows){
                    resultImage.at<cv::Vec3b>(i,j) = srcImage.ptr<cv::Vec3b>(y)[x];
                }
            }
        }

        jint* ptr = (jint*)resultImage.ptr(0);

        int size = w * h;
//        jintArray result = env->NewIntArray(size);
        env->SetIntArrayRegion(buf, 0, size, ptr);
        env->ReleaseIntArrayElements(buf, cbuf, 0);
//        return result;
    }
    /**
     * 平移2
     */
    JNIEXPORT void JNICALL Java_com_eyck_opencvdemo_utils_JNI_translateFullProc(JNIEnv *env, jobject instance,
                                                                       jintArray buf, jint w, jint h, jint xOffSet, jint yOffSet) {

        jint *cbuf;
        cbuf = env->GetIntArrayElements(buf, false);
        if (cbuf == NULL) {
            return ;
        }



        Mat srcImage(h, w, CV_8UC4, (unsigned char*) cbuf);

        int rows = srcImage.rows+xOffSet;
        int cols = srcImage.cols+yOffSet;
        //输出矩阵
        cv::Mat resultImage(rows,cols,srcImage.type());

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int x = j-xOffSet;
                int y = i-yOffSet;
                //边界判断 ptr<cv::Vec3b>(y)获取第y行的首地址
                if (x>=0 && y>=0 && x<cols && y<rows){
                    resultImage.at<cv::Vec3b>(i,j) = srcImage.ptr<cv::Vec3b>(y)[x];
                }
            }
        }

        jint* ptr = (jint*)resultImage.ptr(0);

//        jintArray bufs = new jint[rows*cols];

        int size = rows * cols;
//        jintArray result = env->NewIntArray(size);
        env->SetIntArrayRegion(buf, 0, size, ptr);
        env->ReleaseIntArrayElements(buf, cbuf, 0);
//        return result;
    }

    /**
     * 缩放
     */
    JNIEXPORT void JNICALL Java_com_eyck_opencvdemo_utils_JNI_scaleProc(JNIEnv *env, jobject instance,
                                                                       jintArray buf, jint w, jint h, jint kx, jint ky) {

        jint *cbuf;
        cbuf = env->GetIntArrayElements(buf, false);
        if (cbuf == NULL) {
            return ;
        }

        Mat srcImage(h, w, CV_8UC4, (unsigned char*) cbuf);

        int rows = srcImage.rows;
        int cols = srcImage.cols;
        //输出矩阵
        cv::Mat resultImage(rows,cols,srcImage.type());

        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                int x = static_cast<int>((i+1)/kx+0.5)-1;
                int y = static_cast<int>((i+1)/ky+0.5)-1;
                //边界判断 ptr<cv::Vec3b>(y)获取第y行的首地址
                resultImage.at<cv::Vec3b>(i,j) = srcImage.at<cv::Vec3b>(x,y);
            }
        }

        jint* ptr = (jint*)resultImage.ptr(0);


        int size = w * h;
//        jintArray result = env->NewIntArray(size);
        env->SetIntArrayRegion(buf, 0, size, ptr);
        env->ReleaseIntArrayElements(buf, cbuf, 0);
//        return result;
    }

    /**
     * 边缘检测
     */
    JNIEXPORT void JNICALL Java_com_eyck_opencvdemo_utils_JNI_cannyProc(JNIEnv *env, jobject instance,
                                                                       jintArray buf, jint w, jint h, jint kx, jint ky) {

        jint *cbuf;
        cbuf = env->GetIntArrayElements(buf, false);
        if (cbuf == NULL) {
            return ;
        }

        Mat srcImage(h, w, CV_8UC3, (unsigned char*) cbuf);

        Mat srcGray;
//        cvtColor(srcImage,srcGray,COLOR_BGRA2BGR);
        cvtColor(srcImage,srcGray,COLOR_BGR2GRAY);


        Mat blurGray;
//        blur(srcImage,blurGray,Size(3,3),Point(-1,-1));
        //腐蚀算法
//        Mat element = getStructuringElement(MORPH_RECT, Size(3, 3));
//        Mat dstImage ,edge,gray;
        //转换为灰度图像
//        cvtColor(srcImage,gray,COLOR_BGRA2GRAY);
        //使用3*3内核去噪
        blur(srcImage,blurGray,Size(3,3));
        //运行Canny算子
//        Canny(blurGray,blurGray,3,9,3);
        jint* ptr = (jint*)srcGray.ptr(0);


        int size = w * h;
//        jintArray result = env->NewIntArray(size);
        env->SetIntArrayRegion(buf, 0, size, ptr);
        env->ReleaseIntArrayElements(buf, cbuf, 0);
    }
}



