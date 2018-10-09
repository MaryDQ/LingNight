package com.mlx.lingnight.ui.activitys;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mlx.lingnight.R;
import com.mlx.lingnight.base.BaseNoTitleActivity;
import com.mlx.lingnight.di.component.AppComponent;
import com.mlx.lingnight.ui.fragments.HomeFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeActivity extends BaseNoTitleActivity {
    private static final String[] CONTENT = new String[]{"生产监控", "综治维稳", "安全环保", "反恐防范", "视频会议", "应急指挥"};
    @BindView(R.id.home_viewpager)
    ViewPager mHomeViewPager;
    @BindView(R.id.home_indicator)
    MagicIndicator mHomeIndicator;

    private ArrayList<Fragment> fragments = new ArrayList<>();

    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    public int getRootViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initViewAndData() {
        initFragment();
        initIndicator();//Indicator初始化
        initListeners();//绑定adapter
    }

    private void initFragment() {
        HomeFragment mHomeFragment ;
        for (int i = 0; i < 6; i++) {
            mHomeFragment= new HomeFragment();
            fragments.add(i, mHomeFragment);
        }
    }

    private void initIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return fragments == null ? 0 : fragments.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setText(CONTENT[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mHomeViewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        mHomeIndicator.setNavigator(commonNavigator);
    }

    private void initListeners() {
        mFragmentPagerAdapter = new TempAdapter(getSupportFragmentManager());
        mHomeViewPager.setAdapter(mFragmentPagerAdapter);
        ViewPagerHelper.bind(mHomeIndicator, mHomeViewPager);
    }

    class TempAdapter extends FragmentPagerAdapter {


        private TempAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);

        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }
    }
}
