package com.paul.schoolbike.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.paul.schoolbike.R;

import java.util.List;

/**
 * Created by Fengli
 * on 2017/7/10  14:35
 * E-mail:2045532295@qq.com
 * Address:北京理工大学珠海学院
 */

public class TabFragmentAdapter extends FragmentStatePagerAdapter {
    private static TabFragmentAdapter adapter;
    private List<Fragment> mFraments;
    private List<String> mTitles;
    public Context mContext;


    public TabFragmentAdapter(FragmentManager fm, Context mContext, List<Fragment> fragments, List<String> tabList) {
        super(fm);//调用父类的构造函数
        this.mContext = mContext;
        mFraments = fragments;
        mTitles = tabList;
    }

    public static TabFragmentAdapter getAdapter(FragmentManager fm, Context mContext, List<Fragment> fragments, List<String> tabList) {
        if (adapter == null) {
            adapter = new TabFragmentAdapter(fm, mContext, fragments, tabList);
        }
        return adapter;
    }

    public static TabFragmentAdapter getAdapter() {
        if (adapter != null) {
            return adapter;
        } else {
            return null;
        }

    }

    public void deleteAdapter(){
    adapter =null;
}
    @Override
    public Fragment getItem(int position) {

        return mFraments.get(position);
    }

    @Override
    public int getCount() {
        return mFraments.size();
    }

    public  CharSequence getPageTitle(int position){
          return  mTitles.get(position);

    }
    public View getTabView(int position) {
        View v = View.inflate(mContext, R.layout.index_tab, null);
        ImageView img = (ImageView) v.findViewById(R.id.image);
        TextView textView=(TextView)v.findViewById(R.id.textView);
        textView.setText(mTitles.get(position));
        switch (position) {
            case 0:
                img.setImageResource(R.drawable.home_icon_selector);
                break;
            case 1:
                img.setImageResource(R.drawable.shopping_icon_selector);
                break;
            case 2:
                img.setImageResource(R.drawable.my_icon_violin_selector);
                break;

        }

        return v;

    }
}