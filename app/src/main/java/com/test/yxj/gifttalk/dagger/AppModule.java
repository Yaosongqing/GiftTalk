package com.test.yxj.gifttalk.dagger;

import com.test.yxj.gifttalk.model.ApiService;
import com.test.yxj.gifttalk.presenter.IGuidePresenter;
import com.test.yxj.gifttalk.presenter.impl.GuidePresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/5.
 */
@Module
public class AppModule  {

    @Provides
    public ApiService providerApiService(){
        ApiService apiService = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiService.class);
        return apiService;
    }

    @Provides
    public IGuidePresenter providePresenter(ApiService apiService){
        return new GuidePresenter(apiService);
    }


}
