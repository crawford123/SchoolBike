package com.paul.schoolbike.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.paul.schoolbike.R;
import com.paul.schoolbike.base.BaseActivity;

/**
 * Created by Fengli
 * on 2017/7/10  14:31
 * E-mail:2045532295@qq.com
 * Address:北京理工大学珠海学院
 */

public class WelcomeActivity  extends BaseActivity  implements  Runnable {
      public  final static int VERSION=1;
      public  static SharedPreferences sp;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
         new Thread(this).start();
    }
    @Override
    public void run() {
            try {
                Thread.sleep(2000);
                sp = getSharedPreferences("Y_Setting", Context.MODE_PRIVATE);
                if (sp.getInt("Version", 0) != VERSION) {
                    startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

     }
}
