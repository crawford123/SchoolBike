package com.paul.schoolbike.widgets;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by Fengli
 * on 2017/7/19  20:23
 * E-mail:2045532295@qq.com
 * Address:北京理工大学珠海学院
 */

public class NetworkImageHolderView<T> implements Holder<T> {
     private ImageView imageView;
    @Override
    public View createView(Context context) {
        imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, T data) {

    }
}
