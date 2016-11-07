package com.test.yxj.gifttalk.presenter;

import com.test.yxj.gifttalk.ui.IGuideSelectView;
import com.test.yxj.gifttalk.ui.IGuideView;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface IGuideOthersPresenter {

    void queryListDatas(int id, int offset);

    void setView(IGuideView guideView);
}
