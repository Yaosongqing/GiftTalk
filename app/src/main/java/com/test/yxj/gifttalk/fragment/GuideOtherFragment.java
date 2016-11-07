package com.test.yxj.gifttalk.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.test.yxj.gifttalk.R;
import com.test.yxj.gifttalk.adapter.GuideOthersListAdapter;
import com.test.yxj.gifttalk.bean.GuideGiftBean;
import com.test.yxj.gifttalk.model.ApiService;
import com.test.yxj.gifttalk.presenter.impl.GuideOthersPresenter;
import com.test.yxj.gifttalk.presenter.impl.GuideSelectPresenter;
import com.test.yxj.gifttalk.ui.IGuideView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/5.
 */
public class GuideOtherFragment extends Fragment implements IGuideView{

    private int offset;
    private int id;
    private List<GuideGiftBean.DataBean.ItemsBean> itemsBeanList = new ArrayList<>();


    private PullToRefreshListView mListView;
    private GuideOthersListAdapter guideOthersListAdapter;

    public GuideOtherFragment(){}

    @SuppressLint("ValidFragment")
    public GuideOtherFragment(int offset, int id) {
        this.offset = offset;
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_guide_other_fragment_view,container,false);
        mListView = (PullToRefreshListView) view.findViewById(R.id.guide_other_lv);
        initPresenter();
        guideOthersListAdapter = new GuideOthersListAdapter(getContext(),itemsBeanList);
        mListView.setAdapter(guideOthersListAdapter);
        return view;
    }

    private void initPresenter() {
        ApiService apiService = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiService.class);
        GuideOthersPresenter guideOthersPresenter = new GuideOthersPresenter(apiService);
        guideOthersPresenter.setView(this);
        guideOthersPresenter.queryListDatas(id,offset);
    }

    @Override
    public void refreshDatas(List datas) {
        itemsBeanList.addAll(datas);
        guideOthersListAdapter.notifyDataSetChanged();
    }
}
