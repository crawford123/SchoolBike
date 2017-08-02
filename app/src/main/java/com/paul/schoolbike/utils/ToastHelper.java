package com.paul.schoolbike.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Zhenaini_Android
 * Description:
 * Created by L.Y
 * Date:2016-05-06
 * Time:16:42
 * Copyright :copyright: 2016年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class ToastHelper {

    public static Toast mToast;

    public static void showToast(int resId, Context context) {
        String text = context.getString(resId);
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showToast(String text, Context context) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
