package com.paul.schoolbike.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Fengli
 * on 2017/7/10  14:32
 * E-mail:2045532295@qq.com
 * Address:北京理工大学珠海学院
 */

public class ViewPagerAdapter extends PagerAdapter{
    private ArrayList<View> views;
    public  ViewPagerAdapter(ArrayList<View> views){
        this.views=views;
    }

    @Override
    public int getCount() {
       if(views!=null){

           return views.size();
       }else{
           return 0;
       }
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }
      /*
instantiateItem(ViewGroup, int) //实例化
 destroyItem(ViewGroup, int, Object) //销毁
 getCount()//获取总数
isViewFromObject(View, Object) //用于确认instantiateItem是否返回了和关键对象有关的Page视图
 */
}
