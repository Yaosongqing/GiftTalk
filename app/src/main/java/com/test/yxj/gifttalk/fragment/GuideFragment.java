package com.test.yxj.gifttalk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.yxj.gifttalk.R;
import com.test.yxj.gifttalk.adapter.GuideTabAdapter;
import com.test.yxj.gifttalk.bean.GuideTabBean;
import com.test.yxj.gifttalk.dagger.DaggerAppComponent;
import com.test.yxj.gifttalk.model.ApiService;
import com.test.yxj.gifttalk.presenter.IGuidePresenter;
import com.test.yxj.gifttalk.presenter.impl.GuidePresenter;
import com.test.yxj.gifttalk.ui.IGuideView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/5.
 */
public class GuideFragment extends Fragment implements IGuideView{

    @BindView(R.id.guide_tablayout)
    TabLayout guideTabLayout;
    @BindView(R.id.guide_view_pager)
    ViewPager guideViewPager;
//    @Inject
//    IGuidePresenter guidePresenter;
    @Inject
    ApiService apiService;

    public static final int OFFSET = 0;
    private List<GuideTabBean.DataBean.ChannelsBean> tabList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private GuideTabAdapter guideTabAdapter;

    public static GuideFragment newInstance(){
        return new GuideFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_guide_fragment_view,container,false);
        ButterKnife.bind(this,view);
        initApiService();
        return view;
    }

    private void initApiService() {
        DaggerAppComponent.create().inject(this);
        IGuidePresenter guidePresenter = new GuidePresenter(apiService);
        guidePresenter.setView(this);
        guidePresenter.queryTabDatas();
    }

    private void initView() {
        guideTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        guideTabLayout.setupWithViewPager(guideViewPager);
    }


    @Override
    public void refreshDatas(List datas) {
        tabList.addAll(datas);
        initFragment();
    }

    private void initFragment() {
        if (tabList != null){
            GuideTabBean.DataBean.ChannelsBean channelsBean = tabList.get(0);
            int id = channelsBean.getId();
            GuideSelectedFragment guideSelectedFragment = new GuideSelectedFragment(OFFSET, id);
            fragmentList.add(guideSelectedFragment);
            for (int i = 1,len = tabList.size(); i < len; i++) {
                GuideTabBean.DataBean.ChannelsBean channelsBean1 = tabList.get(i);
                int id1 = channelsBean1.getId();
                GuideOtherFragment guideOtherFragment = new GuideOtherFragment(OFFSET, id1);
                fragmentList.add(guideOtherFragment);
            }
            guideTabAdapter = new GuideTabAdapter(getFragmentManager(),tabList,fragmentList);
            guideViewPager.setAdapter(guideTabAdapter);
            initView();
        }
    }
}
