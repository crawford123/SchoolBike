package com.paul.schoolbike.base;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paul.schoolbike.R;

import butterknife.BindView;

/**
 * Created by Fengli
 * on 2017/7/18  10:55
 * E-mail:2045532295@qq.com
 * Address:北京理工大学珠海学院
 */

public abstract  class  BaseHeadActivity extends BaseActivity {

    @BindView(R.id.iv_left_opt)
    ImageView ivLeftOpt;
    @BindView(R.id.tv_left_opt)
    TextView tvLeftOpt;
    @BindView(R.id.btn_left_opt)
    LinearLayout btnLeftOpt;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_opt)
    ImageView ivOpt;
    @BindView(R.id.uncheckNum)
    TextView uncheckNum;
    @BindView(R.id.tv_opt)
    TextView tvOpt;
    @BindView(R.id.btn_opt)
    FrameLayout btnOpt;
    @BindView(R.id.r1_title_1)
    RelativeLayout r1Title1;

    @Override
    protected int getLayoutRes() {
        return R.layout.inc_title;
    }

    @Override
    protected void init() {

    }

    /**
     * 设置标题
     */

    protected void setTitle(String title) {
        if (tvTitle == null) {
            tvTitle = (TextView) findViewById(R.id.tv_title);
        }
        tvTitle.setText(title);
        tvTitle.setVisibility(View.VISIBLE);
    }

    /**
     * 设置标题颜色
     */
    protected void setTitleColor(int textColor, int backgroud) {
        if (tvTitle != null) {
            if (backgroud != -1) {
                r1Title1.setBackgroundColor(backgroud);
            }
            if (textColor != -1) {
                tvTitle.setTextColor(textColor);
            }
            tvTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题颜色
     */
    protected void setTitleColorRes(int textColorRes, int backgroudRes) {
        if (tvTitle != null) {
            if (backgroudRes != -1) {
                r1Title1.setBackgroundColor(getResources().getColor(backgroudRes));
            }
            if (textColorRes != -1) {
                tvTitle.setTextColor(getResources().getColor(textColorRes));
            }
            tvTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置右操作图片
     *
     * @param resId
     */
    protected void setIvOpt(int resId) {
        if (ivOpt != null) {
            ivOpt.setImageResource(resId);
            ivOpt.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置右操作文字
     *
     * @param opt
     */
    protected void setTvOpt(String opt) {
        if (tvOpt == null) {
            tvOpt = (TextView) findViewById(R.id.tv_opt);
        }
        tvOpt.setVisibility(View.VISIBLE);
        tvOpt.setText(opt);

    }

    /**
     * 设置右操作文字
     *
     * @param opt
     */
    protected void setTvOpt(String opt, int textColor) {
        if (tvOpt == null) {
            tvOpt = (TextView) findViewById(R.id.tv_opt);
        }
        tvOpt.setText(opt);
        tvOpt.setTextColor(textColor);
        tvOpt.setVisibility(View.VISIBLE);
    }

    /**
     * 设置右操作事件
     *
     * @param optListener
     */
    protected void setOptListener(View.OnClickListener optListener) {
        if (btnOpt == null) {
            btnOpt = (FrameLayout) findViewById(R.id.btn_opt);
        }
        btnOpt.setOnClickListener(optListener);
        btnOpt.setVisibility(View.VISIBLE);
    }

    /**
     * 设置左操作图片
     *
     * @param resId
     */
    protected void setIvLeftOpt(int resId) {
        if (ivLeftOpt == null) {
            ivLeftOpt = (ImageView) findViewById(R.id.iv_left_opt);
        }
        ivLeftOpt.setImageResource(resId);
        ivLeftOpt.setVisibility(View.VISIBLE);
    }

    /**
     * 设置左操作文字
     *
     * @param opt
     */
    protected void setTvLeftOpt(String opt) {
        tvLeftOpt.setText(opt);
        tvLeftOpt.setVisibility(View.VISIBLE);
    }

    /**
     * 设置左操作事件
     *
     * @param optListener
     */
    protected void setLeftOptListener(View.OnClickListener optListener) {
        if (btnLeftOpt == null) {
            btnLeftOpt = (LinearLayout) findViewById(R.id.btn_left_opt);
        }
        btnLeftOpt.setOnClickListener(optListener);
        btnLeftOpt.setVisibility(View.VISIBLE);
    }


}
