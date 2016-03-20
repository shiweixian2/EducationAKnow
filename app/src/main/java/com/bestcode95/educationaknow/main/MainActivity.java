package com.bestcode95.educationaknow.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.bestcode95.educationaknow.R;
import com.bestcode95.educationaknow.utils.view.BottomTab;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private static final int LEFT_FRAGMENT = 0;
    private static final int CENTER_FRAGMENT = 1;
    private static final int RIGHT_FRAGMENT = 2;
    FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager fragmentViews;
    private List<BottomTab> bottomTabList = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    /**
     * 初始化界面组件
     */
    private void initView() {
        fragmentViews = (ViewPager) findViewById(R.id.fragment_pages);
        BottomTab tab1 = (BottomTab) findViewById(R.id.bottom_tab1);
        bottomTabList.add(tab1);
        BottomTab tab2 = (BottomTab) findViewById(R.id.bottom_tab2);
        bottomTabList.add(tab2);
        BottomTab tab3 = (BottomTab) findViewById(R.id.bottom_tab3);
        bottomTabList.add(tab3);
        fragmentViews.setOnPageChangeListener(this);
        fragmentViews.setCurrentItem(0, false);
//        bottomTabList.get(0).setIconAlpha(1.0f);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        //配置数据
        initData();
    }

    /**
     * 初始化数据，配置fragments
     */
    private void initData() {
        //设置fragment
        LeftFragment leftFragment = new LeftFragment();
        CenterFragment centerFragment = new CenterFragment();
        RightFragment rightFragment = new RightFragment();
        fragments.add(leftFragment);
        fragments.add(centerFragment);
        fragments.add(rightFragment);
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        //为ViewPager配置adapter
        fragmentViews.setAdapter(fragmentPagerAdapter);
    }


    @Override
    public void onClick(View v) {
        Log.e("MainActivity", "onClick");
        resetOtherTabs();

        switch (v.getId()) {
            case R.id.bottom_tab1:
                bottomTabList.get(0).setTargetBitmap(R.drawable.bottom_tab_left_icon_selected);
                //第二个参数为true有动画效果
                fragmentViews.setCurrentItem(0, false);
                Log.e("MainActivity", "fragmentViews.setCurrentItem(0, false);");
                break;
            case R.id.bottom_tab2:
                bottomTabList.get(1).setTargetBitmap(R.drawable.bottom_tab_center_icon_selected);
                //第二个参数为true有动画效果
                fragmentViews.setCurrentItem(1, false);
                Log.e("MainActivity", "fragmentViews.setCurrentItem(1, false);");
                break;
            case R.id.bottom_tab3:
                bottomTabList.get(2).setTargetBitmap(R.drawable.bottom_tab_right_icon_selected);
                //第二个参数为true有动画效果
                fragmentViews.setCurrentItem(2, false);
                Log.e("MainActivity", "fragmentViews.setCurrentItem(2, false);");
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        resetOtherTabs();
//
//        switch (position) {
//            case LEFT_FRAGMENT:
//                bottomTabList.get(0).setTargetBitmap(R.drawable.bottom_tab_left_icon_selected);
//                //第二个参数为true有动画效果
//                fragmentViews.setCurrentItem(0, false);
//                Log.e("MainActivity", "fragmentViews.setCurrentItem(0, false);");
//                break;
//            case CENTER_FRAGMENT:
//                bottomTabList.get(1).setTargetBitmap(R.drawable.bottom_tab_center_icon_selected);
//                //第二个参数为true有动画效果
//                fragmentViews.setCurrentItem(1, false);
//                Log.e("MainActivity", "fragmentViews.setCurrentItem(1, false);");
//                break;
//            case RIGHT_FRAGMENT:
//                bottomTabList.get(2).setTargetBitmap(R.drawable.bottom_tab_right_icon_selected);
//                //第二个参数为true有动画效果
//                fragmentViews.setCurrentItem(2, false);
//                Log.e("MainActivity", "fragmentViews.setCurrentItem(2, false);");
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void onPageSelected(int position) {
        resetOtherTabs();

        switch (position) {
            case LEFT_FRAGMENT:
                bottomTabList.get(0).setTargetBitmap(R.drawable.bottom_tab_left_icon_selected);
                //第二个参数为true有动画效果
                fragmentViews.setCurrentItem(0, false);
                Log.e("MainActivity", "fragmentViews.setCurrentItem(0, false);");
                break;
            case CENTER_FRAGMENT:
                bottomTabList.get(1).setTargetBitmap(R.drawable.bottom_tab_center_icon_selected);
                //第二个参数为true有动画效果
                fragmentViews.setCurrentItem(1, false);
                Log.e("MainActivity", "fragmentViews.setCurrentItem(1, false);");
                break;
            case RIGHT_FRAGMENT:
                bottomTabList.get(2).setTargetBitmap(R.drawable.bottom_tab_right_icon_selected);
                //第二个参数为true有动画效果
                fragmentViews.setCurrentItem(2, false);
                Log.e("MainActivity", "fragmentViews.setCurrentItem(2, false);");
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 重置其他的tABIndicator的颜色
     */
    private void resetOtherTabs() {
        Log.e("MainActivity", "resetOtherTabs");
        int resIds[] = new int[]{R.drawable.bottom_tab_left_icon_normal,
                R.drawable.bottom_tab_center_icon_normal, R.drawable.bottom_tab_right_icon_normal};
        for (int i = 0; i < bottomTabList.size(); i++) {
            bottomTabList.get(i).setTargetBitmap(resIds[i]);
        }
    }
}