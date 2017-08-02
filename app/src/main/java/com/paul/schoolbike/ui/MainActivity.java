package com.paul.schoolbike.ui;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import com.paul.schoolbike.R;
import com.paul.schoolbike.adapter.TabFragmentAdapter;
import com.paul.schoolbike.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorlayout;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        viewpager.setOffscreenPageLimit(4);//防止首页嵌套viewPager被回收
        List<String> tabList = new ArrayList<>();
        tabList.add("首页");
        tabList.add("商城");
        tabList.add("我的");
        List<Fragment> fragmentList = new ArrayList<>();
        IndexFragment indexFragment = new IndexFragment();
        fragmentList.add(indexFragment);
        MallFragment mallFragment = new MallFragment();
        fragmentList.add(mallFragment);
        MineFragment mineFragment = new MineFragment();
        fragmentList.add(mineFragment);
        final TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), context, fragmentList, tabList);
        viewpager.setAdapter(fragmentAdapter);///给ViewPager设置适配器
        tabs.setupWithViewPager(viewpager);
        tabs.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器
        for (int i = 0; i < tabs.getTabCount(); i++) {
            TabLayout.Tab tab = tabs.getTabAt(i);
            tab.setCustomView(fragmentAdapter.getTabView(i));
        }

        viewpager.addOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        tabs.getTabAt(position).select();//相应位置的标签被选定
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }


        );
        tabs.getTabAt(0).getCustomView().setSelected(true);//位置为0的标签一开始被选定
    }


}
