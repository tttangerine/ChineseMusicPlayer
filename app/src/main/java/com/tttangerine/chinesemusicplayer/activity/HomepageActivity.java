package com.tttangerine.chinesemusicplayer.activity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tttangerine.chinesemusicplayer.R;
import com.tttangerine.chinesemusicplayer.fragment.HomepageFragment;
import com.tttangerine.chinesemusicplayer.fragment.MusicListFragment;
import com.tttangerine.chinesemusicplayer.fragment.SchoolInfoFragment;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;

    List<Fragment> fragments = new ArrayList<>(3);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initView();
    }

    private void initView(){
        //find views
        mViewPager = findViewById(R.id.vp_fragment);
        mTabRadioGroup = findViewById(R.id.rg_tabs);
        //init fragment
        fragments.add(new SchoolInfoFragment());
        fragments.add(new HomepageFragment());
        fragments.add(new MusicListFragment());
        //init view pager
        FragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setCurrentItem(1);
        //register listener
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) mTabRadioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) { }
    };

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++){
                if (group.getChildAt(i).getId() == checkedId){
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        private List<Fragment> mList;

        MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list){
            super(fm);
            this.mList = list;
        }

        @NonNull
        @Override
        public Fragment getItem(int position){
            return this.mList.get(position);
        }

        @Override
        public int getCount(){
            return this.mList == null ? 0 : this.mList.size();
        }

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
    }

}
