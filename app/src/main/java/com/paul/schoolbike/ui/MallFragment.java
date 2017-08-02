package com.paul.schoolbike.ui;

import com.paul.schoolbike.R;
import com.paul.schoolbike.base.BaseHeadFragment;

public class MallFragment extends BaseHeadFragment {

    protected  void setHeadView(){

        setTitle("商城");
    }
    @Override
    protected void init() {
        setHeadView();

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mall;
    }






}
