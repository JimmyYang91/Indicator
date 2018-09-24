package com.example.tonk1.indicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ViewPagerIndicator indicator;

    private List<String> titles = Arrays.asList("短信1", "收藏2", "推荐3","短信4", "收藏5", "推荐6","短信7", "收藏8", "推荐9");
    private List<ViewPagerSimpleFragment> mContents = new ArrayList<ViewPagerSimpleFragment>();
    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return mContents.get(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initData();
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.setscroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViewPager = (ViewPager) this.findViewById(R.id.viewPager);
        indicator = (ViewPagerIndicator) this.findViewById(R.id.indicator);
    }

    private void initData() {
        for(String title: titles){
            mContents.add(ViewPagerSimpleFragment.newInstance(title));
        }
    }


}
