package com.eyck.opencvdemo.controller.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.eyck.opencvdemo.R;
import com.eyck.opencvdemo.controller.base.BaseFragment;
import com.eyck.opencvdemo.controller.fragment.CoreFragment;
import com.eyck.opencvdemo.controller.fragment.Feature2DFragment;
import com.eyck.opencvdemo.controller.fragment.ImgprocFragment;
import com.eyck.opencvdemo.controller.fragment.OpenCVBaseFragment;
import com.eyck.opencvdemo.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.setting_main)
    LinearLayout settingMain;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    /**
     * 切换不同Fragment
     */
    public List<BaseFragment> fragments;
    private int curPosition = 0;
    public Fragment curFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new OpenCVBaseFragment());
        fragments.add(new CoreFragment());
        fragments.add(new ImgprocFragment());
        fragments.add(new Feature2DFragment());
        //默认显示主页
        curPosition = 0;
        Fragment desFragment = fragments.get(0);
        switchFragment(curFragment, desFragment,curPosition);
    }

    public void switchFragment(Fragment fromFragment, Fragment desFragment, int curP) {
        curPosition = curP;
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (desFragment != fromFragment) {
            if (desFragment != null) {
                curFragment = desFragment;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (desFragment.isAdded()) {
                    if (fromFragment != null) {
                        ft.hide(fromFragment);
                    }
                    if (desFragment != null) {
                        ft.show(desFragment).commit();
                    }
                } else {
                    if (fromFragment != null) {
                        ft.hide(fromFragment);
                    }
                    if (desFragment != null) {
                        ft.add(R.id.fl_main, desFragment).commit();
                    }
                }
            }
        }
    }

    private void initListener() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.recomment_main :
                        curPosition = 0;
                        break;
                    case R.id.msg_main :
                        curPosition = 1;
                        break;
                    case R.id.favorite_main :
                        curPosition = 2;
                        break;
                    case R.id.stusys_main :
                        curPosition = 3;
                        break;
                }
                switchFragment(curFragment, fragments.get(curPosition),curPosition);
                return false;
            }
        });
    }

    @OnClick(R.id.setting_main)
    public void onViewClicked() {
        MyLog.w("设置");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
//
//
//    public void original(View view){
//        iv_activity_main.setImageBitmap(bitmap);
//    }
//
//    public void api(View view){
//        iv_activity_main.setImageBitmap(JavaImageUtils.getImageProcess(bitmap));
//    }
//
//    public void ndk(View view){
//        iv_activity_main.setImageBitmap(JavaImageUtils.getImageNDK(bitmap));
//    }
//
//    public void btnGrayProcessNDK(View view){
//        iv_activity_main.setImageBitmap(JavaImageUtils.grayProcessNDK(bitmap));
//    }
//
//    public void btnGrayProcessJava(View view){
//        iv_activity_main.setImageBitmap(JavaImageUtils.grayProcessJava(bitmap));
//    }
//
//
//
//    public void btnBlur(View view){
//        iv_activity_main.setImageBitmap(JavaImageUtils.blurProcessNDK(bitmap));
//    }
//
//    public void btnErode(View view){
//        iv_activity_main.setImageBitmap(JavaImageUtils.erodeProcessNDK(bitmap));
//    }
//
//    public void btnRemap(View view){
//        iv_activity_main.setImageBitmap(JavaImageUtils.remapProcessNDK(bitmap));
//    }
//
//    public void btnTrans1(View view){
//        iv_activity_main.setImageBitmap(JavaImageUtils.translateProcessNDK(bitmap));
//    }
//
//    public void btnTrans2(View view){
//        iv_activity_main.setImageBitmap(JavaImageUtils.translateFullProcessNDK(bitmap));
//    }
//
//    public void btnScale(View view){
//        iv_activity_main.setImageBitmap(JavaImageUtils.scaleProcessNDK(bitmap));
//    }


}
