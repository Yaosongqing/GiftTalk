package com.test.yxj.gifttalk.model;

import com.test.yxj.gifttalk.bean.GuideGiftBean;
import com.test.yxj.gifttalk.bean.GuideTabBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/5.
 */
public interface ApiService {

    @GET("v2/channels/preset?gender=1&generation=2")
    Observable<GuideTabBean> queryTabDatas();

    @GET("v2/channels/{id}/items?gender=1&generation=2&limit=20")
    Observable<GuideGiftBean> queryListDatas(@Path("id") int id,@Query("offset")int offset);
}
