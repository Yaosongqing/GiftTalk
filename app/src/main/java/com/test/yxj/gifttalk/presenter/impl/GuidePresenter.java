package com.test.yxj.gifttalk.presenter.impl;

import com.test.yxj.gifttalk.bean.GuideTabBean;
import com.test.yxj.gifttalk.model.ApiService;
import com.test.yxj.gifttalk.presenter.IGuidePresenter;
import com.test.yxj.gifttalk.ui.IGuideView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/5.
 */
public class GuidePresenter implements IGuidePresenter {

    private ApiService apiService;
    private IGuideView guideView;

    public GuidePresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    public void setView(IGuideView guideView){
        this.guideView = guideView;
    }

    @Override
    public void queryTabDatas() {
        apiService.queryTabDatas()
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<GuideTabBean, List<GuideTabBean.DataBean.ChannelsBean>>() {
                    @Override
                    public List<GuideTabBean.DataBean.ChannelsBean> call(GuideTabBean guideTabBean) {
                        List<GuideTabBean.DataBean.ChannelsBean> channelsBeanList = guideTabBean.getData().getChannels();
                        return channelsBeanList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<GuideTabBean.DataBean.ChannelsBean>>() {
                    @Override
                    public void call(List<GuideTabBean.DataBean.ChannelsBean> channelsBeen) {
                        guideView.refreshDatas(channelsBeen);
                    }
                });
    }
}
