package com.test.yxj.gifttalk.dagger;

import android.support.v4.app.Fragment;

import com.test.yxj.gifttalk.fragment.GuideFragment;

import dagger.Component;

/**
 * Created by Administrator on 2016/11/5.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(GuideFragment fragment);
}
