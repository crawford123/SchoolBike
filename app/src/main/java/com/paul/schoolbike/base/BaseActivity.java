package com.paul.schoolbike.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.paul.schoolbike.R;
import com.paul.schoolbike.utils.ToastHelper;
import com.paul.schoolbike.utils.Util;

import butterknife.ButterKnife;

/**
 * Created by Fengli
 * on 2017/7/10  14:35
 * E-mail:2045532295@qq.com
 * Address:北京理工大学珠海学院
 */

public abstract class BaseActivity  extends AppCompatActivity{
    protected Context  context=null;
     private SharedPreferences preferences = null;
    Context sharedContext = null;
    private ProgressDialog progressDialog;
    protected boolean isHard = false;
    boolean isbackground = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;

        if (getLayoutRes() != 0) {
            int res = getLayoutRes();
            setContentView(res);
            try {
                ButterKnife.bind(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            init();
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

    }


    public SharedPreferences getMyPreferences() {
        // TODO Auto-generated method stub
        if (preferences != null)
            return preferences;
        try {
            sharedContext = createPackageContext(Util.getPackageName(this), Context.CONTEXT_IGNORE_SECURITY);
            preferences = sharedContext.getSharedPreferences("config", Context.MODE_PRIVATE);
        } catch (PackageManager.NameNotFoundException e) {
//            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return preferences;
    }

    protected  abstract  int getLayoutRes();

    protected  abstract  void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    protected void saveP(String key, String value) {
        SharedPreferences preferences = getMyPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    protected void saveP(String key, Long value) {
        SharedPreferences preferences = getMyPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    protected void saveP(String key, Boolean value) {
        SharedPreferences preferences = getMyPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    protected boolean loadP(String key, Boolean dvalue) {
        SharedPreferences preferences = getMyPreferences();
        return preferences.getBoolean(key, dvalue);
    }

    protected long loadP(String key, Long dvalue) {
        SharedPreferences preferences = getMyPreferences();
        return preferences.getLong(key, dvalue);
    }

    protected String loadP(String key) {
        SharedPreferences preferences = getMyPreferences();
        return preferences.getString(key, null);
    }

    protected void clearP(String key) {
        SharedPreferences preferences = getMyPreferences();
        preferences.edit().remove(key).commit();
    }

    public void toast(String msg) {
        ToastHelper.showToast(msg, getApplicationContext());
    }

    protected void toast(int msg_id) {
        ToastHelper.showToast(getString(msg_id), this);
    }

    /**
     * 转跳 没有finish 没有切换效果
     *
     * @param classObj
     */
    public void toActivity(Class<?> classObj) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        startActivity(intent);
    }
    // //////////////////////////////////////////////////////////////////////////////////////////////
    // logic method

    /**
     * 转跳 没有finish
     *
     * @param classObj
     */
    public void overlay(Class<?> classObj) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    /**
     * 转跳 没有finish
     *
     * @param classObj
     * @param params
     */
    public void overlay(Class<?> classObj, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        intent.putExtras(params);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////
    // debug method

    /**
     * 转跳 没有finish
     *
     * @param classObj
     * @param requestCode
     * @param params
     */
    public void overlayLeftForResult(Class<?> classObj, int requestCode, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        intent.putExtras(params);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
    }

    /**
     * 转跳 没有finish
     *
     * @param classObj
     * @param requestCode
     */
    public void overlayForResult(Class<?> classObj, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    /**
     * 转跳 没有finish
     *
     * @param classObj
     * @param requestCode
     * @param params
     */
    public void overlayForResult(Class<?> classObj, int requestCode, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        intent.putExtras(params);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    /**
     * 转跳 有finish
     *
     * @param classObj
     */
    public void forward(Class<?> classObj) {
        Intent intent = new Intent();
        intent.setClass(this, classObj);
        this.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
        this.finish();
    }

    /**
     * 转跳 有finish
     *
     * @param classObj
     * @param params
     */
    public void forward(Class<?> classObj, Bundle params) {
        Intent intent = new Intent();
        intent.setClass(this, classObj);
        intent.putExtras(params);
        this.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
        this.finish();
    }

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        onFinish();
//        scrollToFinishActivity();
    }

    public void onFinish() {
        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
    }


    public void setIsHard(boolean isHard) {
        this.isHard = isHard;
    }

    public void setIsbackgroundLoading(boolean isbackground) {
        this.isbackground = isbackground;
    }

    /**
     * 打开waiting dialog
     */
    public void showLoadBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.dismiss();
        progressDialog.setMessage("请骚等...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }



    public void showLoadBar(String Message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.dismiss();
        progressDialog.setMessage(Message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideLoadBar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
