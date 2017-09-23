package com.eyck.opencvdemo.controller.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.eyck.opencvdemo.R;
import com.eyck.opencvdemo.controller.base.BaseFragment;
import com.eyck.opencvdemo.utils.JavaImageUtils;
import com.eyck.opencvdemo.utils.MyLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Eyck on 2017/8/27.
 */

public class OpenCVBaseFragment extends BaseFragment {
    @Bind(R.id.iv_opencv)
    ImageView ivOpencv;
    @Bind(R.id.btn1_opencv)
    Button btn1Opencv;
    @Bind(R.id.btn2_opencv)
    Button btn2Opencv;
    @Bind(R.id.btn3_opencv)
    Button btn3Opencv;
    @Bind(R.id.btn4_opencv)
    Button btn4Opencv;
    Bitmap mBitmap;
    @Bind(R.id.btn5_opencv)
    Button btn5Opencv;
    @Bind(R.id.btn6_opencv)
    Button btn6Opencv;
    @Bind(R.id.btn7_opencv)
    Button btn7Opencv;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_opencv_base, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.lena);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn1_opencv, R.id.btn2_opencv, R.id.btn3_opencv, R.id.btn4_opencv,R.id.btn5_opencv, R.id.btn6_opencv, R.id.btn7_opencv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1_opencv:
                MyLog.w("btn1_opencv");
                ivOpencv.setImageBitmap(mBitmap);
                break;
            case R.id.btn2_opencv:
                MyLog.w("修改RGB");
                ivOpencv.setImageBitmap(JavaImageUtils.getImageNDK(mBitmap));
                break;
            case R.id.btn3_opencv:
                MyLog.w("图像灰度");
                ivOpencv.setImageBitmap(JavaImageUtils.grayProcessNDK(mBitmap));
                break;
            case R.id.btn4_opencv:
                MyLog.w("图像平滑");
                ivOpencv.setImageBitmap(JavaImageUtils.blurProcessNDK(mBitmap));
                break;
            case R.id.btn5_opencv:
                MyLog.w("图像腐蚀");
                ivOpencv.setImageBitmap(JavaImageUtils.erodeProcessNDK(mBitmap));
                break;
            case R.id.btn6_opencv:
                MyLog.w("图像重映射");
                ivOpencv.setImageBitmap(JavaImageUtils.remapProcessNDK(mBitmap));
                break;
            case R.id.btn7_opencv:
                MyLog.w("图像边缘检测");
                ivOpencv.setImageBitmap(JavaImageUtils.cannyProcessNDK(mBitmap));
                break;
        }
    }

}
