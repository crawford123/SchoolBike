package com.paul.schoolbike.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.paul.schoolbike.R;
import com.paul.schoolbike.adapter.ViewPagerAdapter;
import com.paul.schoolbike.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Fengli
 * on 2017/7/10  14:31
 * E-mail:2045532295@qq.com
 * Address:北京理工大学珠海学院
 */

public class GuideActivity  extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private View view1,view2,view3;
    private ArrayList<View>  views=new ArrayList<>();


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    protected void init() {
        LayoutInflater  layoutInflater=LayoutInflater.from(this);
        view1=layoutInflater.inflate(R.layout.guide_view1,null);
        view2=layoutInflater.inflate(R.layout.guide_view2,null);
        view3=layoutInflater.inflate(R.layout.guide_view3,null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        viewPager.setOnPageChangeListener(this);
        viewPager.setAdapter(new ViewPagerAdapter(views));
        view3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor  SE=WelcomeActivity.sp.edit();
                        SE.putInt("Version",WelcomeActivity.VERSION);
                        SE.commit();
                        startActivity(new Intent(GuideActivity.this,MainActivity.class));
                        finish();
                    }
                });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
