package com.test.yxj.gifttalk.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.test.yxj.gifttalk.R;
import com.test.yxj.gifttalk.fragment.GuideFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private FragmentManager supportFragmentManager;
    private Fragment mCurrentFragment;
    private GuideFragment guideFragment;


    @BindView(R.id.main_select_rg)
    RadioGroup selectRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        supportFragmentManager = getSupportFragmentManager();
        initFragment();
    }

    private void initFragment(){
        guideFragment = GuideFragment.newInstance();
        selectRG.check(R.id.main_guide_rb);
        changeFragment(guideFragment);
        selectRG.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_guide_rb:
                changeFragment(guideFragment);
                break;
            case R.id.main_hot_rb:
                break;
            case R.id.main_sort_rb:
                break;
            case R.id.main_mine_rb:
                break;
        }
    }


    private void changeFragment(Fragment fragment){
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        if(mCurrentFragment != null){
            transaction.hide(mCurrentFragment);
        }if(!fragment.isAdded()){
            transaction.add(R.id.main_fragment_content_fl,fragment);
        }else{
            transaction.show(fragment);
        }
        transaction.commit();
        mCurrentFragment = fragment;
    }


}
