package com.test.yxj.gifttalk.ui;

import com.test.yxj.gifttalk.bean.GuideGiftBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface IGuideSelectView {

    void refreshDatas(List<String> dateList, Map<String,List<GuideGiftBean.DataBean.ItemsBean>> map);
}
