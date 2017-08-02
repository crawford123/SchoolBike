package com.paul.schoolbike.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.paul.schoolbike.R;
import com.paul.schoolbike.utils.SharePreferencesProvider;
import com.paul.schoolbike.utils.ToastHelper;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by Fengli
 * on 2017/7/19  11:12
 * E-mail:2045532295@qq.com
 * Address:北京理工大学珠海学院
 */

public  abstract class BaseFragment  extends Fragment {

        protected  static  String TAG_LOG=null;
        public Gson gson=new Gson();
        protected Context mContext;
        protected Activity mActivity;
    /**
     * Screen information
     */

      protected int mScreenWidth = 0;
      protected int mScreenHeight = 0;
      protected float mScreenDensity = 0.0f;
      View parent;
      LayoutInflater inflater;
      private  int mTaskId;
      private boolean isFirstResume = true;
      private boolean isFirstVisible = true;
      private boolean isFirstInvisible = true;
      private boolean isPrepared;

      public final String TAG = this.getClass().getSimpleName();
    // //////////////////////////////////////////////////////////////////////////////////////////////
    // common classes
      protected boolean isHard = false;
     Context sharedContext = null;
    // //////////////////////////////////////////////////////////////////////////////////////////////
    // logic method
      boolean isbackground = false;
    // //////////////////////////////////////////////////////////////////////////////////////////////
    // debug method
      private SharedPreferences preferences = null;
      private ProgressDialog progressDialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity=activity;
        mContext=activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         TAG_LOG=this.getClass().getSimpleName();
         mTaskId=getActivity().getTaskId();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          if(getLayoutRes()!=0){
               this.parent=inflater.inflate(getLayoutRes(),container,false);
               this.inflater=inflater;
                try{

                    ButterKnife.bind(this,parent);
                }catch (Exception e){
                    e.printStackTrace();
                }
                init();
              return  parent;
          }else{
               return  super.onCreateView(inflater,container,savedInstanceState);
          }


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenDensity=displayMetrics.density;
        mScreenHeight=displayMetrics.heightPixels;
        mScreenWidth=displayMetrics.widthPixels;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try{
            Field childFragmentManager=Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this,null);

        }catch (NoSuchFieldException e){
             throw new  RuntimeException(e);

        }catch (IllegalAccessException e){
            throw  new RuntimeException(e);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isFirstResume){

            isFirstResume=false;
            return;
        }
        if (getUserVisibleHint()){
             onUserInvisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(getUserVisibleHint()){

             onUserInvisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isFirstVisible) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        }else {

            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }


    }
    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }
    /**
     * when fragment is visible for the first time, here we can do some initialized work or refresh data only once
     */
    protected abstract void onFirstUserVisible();

    /**
     * this method like the fragment's lifecycle method onResume()
     */
    protected abstract void onUserVisible();

    /**
     * when fragment is invisible for the first time
     */
    private void onFirstUserInvisible() {
        // here we do not recommend do something
    }

    /**
     * this method like the fragment's lifecycle method onPause()
     */
    protected abstract void onUserInvisible();

    /**
     * init all views and add events
     */
    /**
     * init all views and add events
     */
    protected abstract void init();

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getLayoutRes();

     protected  View findViewById(int id){

         return  parent.findViewById(id);
     }
    /**
     * 查找view
     */
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }


    public static String loadP(String key, Context context) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(context);
        return preferences.getString(key, null);
    }

    public static void saveP(String key, String value, Context context) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 转跳 没有finish 没有切换效果
     *
     * @param classObj
     */
    public void toActivity(Class<?> classObj) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(mActivity, classObj);
        startActivity(intent);
    }

    /**
     * 转跳 没有finish
     *
     * @param classObj
     */
    public void overlay(Class<?> classObj) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(mActivity, classObj);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    /**
     * 转跳 没有finish
     *
     * @param classObj
     * @param params
     */
    public void overlay(Class<?> classObj, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(mActivity, classObj);
        intent.putExtras(params);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    /**
     * 转跳 没有finish
     *
     * @param classObj
     * @param requestCode
     */
    public void overlayForResult(Class<?> classObj, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(mActivity, classObj);
        startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
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
        intent.setClass(mActivity, classObj);
        intent.putExtras(params);
        startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    /**
     * 转跳 没有finish
     *
     * @param classObj
     * @param requestCode
     * @param params
     */
    public void overlayLeftForResult(Class<?> classObj, int requestCode, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(mActivity, classObj);
        intent.putExtras(params);
        startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
    }

    /**
     * 转跳 有finish
     *
     * @param classObj
     */
    public void forward(Class<?> classObj) {
        Intent intent = new Intent();
        intent.setClass(mActivity, classObj);
        this.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
        mActivity.finish();
    }

    /**
     * 转跳 有finish
     *
     * @param classObj
     * @param params
     */
    public void forward(Class<?> classObj, Bundle params) {
        Intent intent = new Intent();
        intent.setClass(mActivity, classObj);
        intent.putExtras(params);
        this.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
        mActivity.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        ActivityTack.getInstanse().removeActivity(getActivity());
    }

    @Override
    public void onStart() {
//        ActivityTack.getInstanse().addActivity(getActivity());
        super.onStart();
    }




    protected void saveP(String key, String value) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    protected void saveP(String key, Boolean value) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    protected void saveP(String key, long value) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    protected boolean loadP(String key, Boolean dvalue) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        return preferences.getBoolean(key, dvalue);
    }

    protected String loadP(String key) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        return preferences.getString(key, null);
    }

    protected void clearP(String key) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        preferences.edit().remove(key).commit();
    }

    public void setIsbackgroundLoading(boolean isbackground) {
        this.isbackground = isbackground;
    }

    public void setIsHard(boolean isHard) {
        this.isHard = isHard;
    }

    public void toast(String msg) {
        ToastHelper.showToast(msg, getActivity());
    }

    protected void toast(int msg_id) {
        ToastHelper.showToast(getString(msg_id), getActivity());
    }

    /**
     * 打开waiting dialog
     */
    public void showLoadBar() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
        }
        progressDialog.dismiss();
        progressDialog.setMessage("请骚等...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void showLoadBar(String Message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
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
