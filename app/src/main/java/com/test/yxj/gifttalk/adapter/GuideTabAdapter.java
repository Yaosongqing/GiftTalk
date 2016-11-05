package com.test.yxj.gifttalk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.test.yxj.gifttalk.bean.GuideTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/5.
 */
public class GuideTabAdapter extends FragmentPagerAdapter {

    private List<GuideTabBean.DataBean.ChannelsBean> tabList;
    private List<Fragment> fragmentList;


    public GuideTabAdapter(FragmentManager fm, List<GuideTabBean.DataBean.ChannelsBean> tabList,List<Fragment> fragmentList) {
        super(fm);
        this.tabList = tabList;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position).getName();
    }
}
