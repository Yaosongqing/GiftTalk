package com.test.yxj.gifttalk.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.yxj.gifttalk.R;

/**
 * Created by Administrator on 2016/11/5.
 */
public class GuideSelectedFragment extends Fragment {

    private int offset;
    private int id;

    public GuideSelectedFragment(){}

    @SuppressLint("ValidFragment")
    public GuideSelectedFragment(int offset, int id) {
        this.offset = offset;
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_guide_selected_fragment_view,container,false);
        return view;
    }
}
