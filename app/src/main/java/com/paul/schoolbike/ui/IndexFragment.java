package com.paul.schoolbike.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.paul.schoolbike.R;
import com.paul.schoolbike.base.BaseHeadFragment;
import com.paul.schoolbike.widgets.LocalImageHolderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends BaseHeadFragment {
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    Unbinder unbinder;
    private List<Integer> localImages = new ArrayList<>();

    protected  void setHeadView(){

        setTitle("悠行");
    }
    @Override
    protected void init() {
        getBanner();
        setHeadView();

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_index;
    }

    protected void getBanner() {
        localImages.add(R.drawable.ironman);
        localImages.add(R.drawable.starrysky);
        localImages.add(R.drawable.scenery);
        localImages.add(R.drawable.minion);
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})

                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置翻页的效果，不需要翻页效果可用不设
               // .setPageTransformer(Transformer.DefaultTransformer);
                .setManualPageable(true);//设置不能手动影响

               convenientBanner.startTurning(5000);//设置翻页间隔时间


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
