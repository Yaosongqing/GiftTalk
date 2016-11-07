package com.test.yxj.gifttalk.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.test.yxj.gifttalk.R;
import com.test.yxj.gifttalk.adapter.GuideSelectedListAdapter;
import com.test.yxj.gifttalk.bean.GuideGiftBean;
import com.test.yxj.gifttalk.model.ApiService;
import com.test.yxj.gifttalk.presenter.IGuideSelectPresenter;
import com.test.yxj.gifttalk.presenter.impl.GuideSelectPresenter;
import com.test.yxj.gifttalk.ui.IGuideSelectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/5.
 */
public class GuideSelectedFragment extends Fragment implements IGuideSelectView{

    private int offset;
    private int id;
    private List<String > dateList = new ArrayList<>();
    private Map<String ,List<GuideGiftBean.DataBean.ItemsBean>> map = new HashMap<>();
    private GuideSelectedListAdapter guideSelectedListAdapter;
//    private ExpandableListView mListView;


    @BindView(R.id.guide_selected_expand_lv)
    ExpandableListView mListView;

    public GuideSelectedFragment(){}

    @SuppressLint("ValidFragment")
    public GuideSelectedFragment(int offset, int id) {
        this.offset = offset;
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_guide_selected_fragment_view,container,false);
        ButterKnife.bind(this,view);
//        mListView = (ExpandableListView) view.findViewById(R.id.guide_selected_expand_lv);
        initPresenter();
        guideSelectedListAdapter = new GuideSelectedListAdapter(getContext(),dateList,map);
        mListView.setAdapter(guideSelectedListAdapter);
        return view;
    }

    private void initPresenter() {
        ApiService apiService = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiService.class);
        GuideSelectPresenter guideSelectPresenter = new GuideSelectPresenter(apiService);
        guideSelectPresenter.setView(this);
        guideSelectPresenter.queryListDatas(id,offset);
    }

    @Override
    public void refreshDatas(List<String> dateList, Map<String, List<GuideGiftBean.DataBean.ItemsBean>> map) {
        this.dateList.addAll(dateList);
        this.map.putAll(map);
        guideSelectedListAdapter.notifyDataSetChanged();
//        setOpen();
    }

    /**
     * 设置默认展开
     */
    private void setOpen(){
        if (mListView != null){
            for (int i = 0; i < mListView.getCount() - mListView.getChildCount() - 1; i++) {
                mListView.expandGroup(i);
            }
        }
    }

}
