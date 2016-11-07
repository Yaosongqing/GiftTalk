package com.test.yxj.gifttalk.presenter.impl;

import com.test.yxj.gifttalk.bean.GuideGiftBean;
import com.test.yxj.gifttalk.model.ApiService;
import com.test.yxj.gifttalk.presenter.IGuideOthersPresenter;
import com.test.yxj.gifttalk.ui.IGuideView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/7.
 */
public class GuideOthersPresenter implements IGuideOthersPresenter {

    private ApiService apiService;
    private IGuideView guideView;

    public GuideOthersPresenter(ApiService apiService) {
        this.apiService = apiService;
    }




    @Override
    public void queryListDatas(int id, int offset) {
        apiService.queryListDatas(id,offset)
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<GuideGiftBean, List<GuideGiftBean.DataBean.ItemsBean> >() {
                    @Override
                    public List<GuideGiftBean.DataBean.ItemsBean> call(GuideGiftBean guideGiftBean) {
                        List<GuideGiftBean.DataBean.ItemsBean> items = guideGiftBean.getData().getItems();
                        return items;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<GuideGiftBean.DataBean.ItemsBean>>() {
                    @Override
                    public void call(List<GuideGiftBean.DataBean.ItemsBean> itemsBeen) {
                        guideView.refreshDatas(itemsBeen);
                    }
                });
    }

    @Override
    public void setView(IGuideView guideView) {
        this.guideView = guideView;
    }
}
