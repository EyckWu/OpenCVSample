package com.eyck.opencvdemo.controller.fragment;

import android.view.View;
import android.widget.TextView;

import com.eyck.opencvdemo.controller.base.BaseFragment;

/**
 * Created by Eyck on 2017/8/27.
 */

public class Feature2DFragment extends BaseFragment {
    @Override
    protected View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("feature2d组件");
        return textView;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
