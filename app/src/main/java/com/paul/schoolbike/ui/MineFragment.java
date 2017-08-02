package com.paul.schoolbike.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paul.schoolbike.R;
import com.paul.schoolbike.base.BaseHeadFragment;


public class MineFragment extends BaseHeadFragment {


    public MineFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }







}
