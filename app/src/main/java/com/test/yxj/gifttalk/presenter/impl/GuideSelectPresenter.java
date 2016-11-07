package com.test.yxj.gifttalk.presenter.impl;

import com.test.yxj.gifttalk.bean.GuideGiftBean;
import com.test.yxj.gifttalk.model.ApiService;
import com.test.yxj.gifttalk.presenter.IGuideSelectPresenter;
import com.test.yxj.gifttalk.tools.DateTool;
import com.test.yxj.gifttalk.ui.IGuideSelectView;
import com.test.yxj.gifttalk.ui.IGuideView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/7.
 */
public class GuideSelectPresenter implements IGuideSelectPresenter {

    private ApiService apiService;
    private IGuideSelectView guideView;

    public GuideSelectPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void setView(IGuideSelectView guideView) {
        this.guideView = guideView;
    }

    @Override
    public void queryListDatas(int id, int offset) {

        final Map<String,List<GuideGiftBean.DataBean.ItemsBean>> map = new HashMap<>();
        final List<String> dateList = new ArrayList<>();
        apiService.queryListDatas(id,offset)
                .subscribeOn(Schedulers.newThread())
                .flatMap(new Func1<GuideGiftBean, Observable<GuideGiftBean.DataBean.ItemsBean>>() {
                    @Override
                    public Observable<GuideGiftBean.DataBean.ItemsBean> call(GuideGiftBean selectBean) {
                        List<GuideGiftBean.DataBean.ItemsBean> items = selectBean.getData().getItems();
                        return Observable.from(items);
                    }
                })
                .map(new Func1<GuideGiftBean.DataBean.ItemsBean, Map<String,List<GuideGiftBean.DataBean.ItemsBean>>>() {
                    @Override
                    public Map<String, List<GuideGiftBean.DataBean.ItemsBean>> call(GuideGiftBean.DataBean.ItemsBean itemsBean) {
                        int published_at = itemsBean.getPublished_at();
                        String date = DateTool.formatDate(published_at * 1000);
                        List<GuideGiftBean.DataBean.ItemsBean> itemsBeanList = map.get(date);
                        if (itemsBeanList == null){
                            itemsBeanList = new ArrayList<>();
                            dateList.add(date);
                            map.put(date,itemsBeanList);
                        }
                        itemsBeanList.add(itemsBean);
                        return map;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String, List<GuideGiftBean.DataBean.ItemsBean>>>() {
                    @Override
                    public void onCompleted() {
                        guideView.refreshDatas(dateList,map);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Map<String, List<GuideGiftBean.DataBean.ItemsBean>> stringListMap) {

                    }
                });
    }


}
