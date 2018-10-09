package com.mlx.lingnight.ui.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlx.lingnight.R;
import com.mlx.lingnight.base.BaseFragment;
import com.mlx.lingnight.di.component.AppComponent;
import com.mlx.lingnight.model.SimpleModel;
import com.mlx.lingnight.ui.adapters.SimpleAdapter;
import com.mlx.lingnight.ui.adapters.ViewHolder;
import com.mlx.lingnight.utils.GlideUtils;
import com.mlx.lingnight.utils.banner_image_loader.BannerImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    private static volatile HomeFragment mHomeFragment;


    public static HomeFragment getInstance(){
        if (null==mHomeFragment) {
            synchronized (HomeFragment.class){
                if (null==mHomeFragment) {
                    mHomeFragment=new HomeFragment();
                }
            }
        }
        return mHomeFragment;
    }


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rcv_home_fragment)
    RecyclerView mRecyclerView;

    private ArrayList<SimpleModel> testList = new ArrayList<>();

    private SimpleAdapter<SimpleModel> mRecyclerAdapter;

    private int number;

    private List<Object> images = new ArrayList<>();

    public HomeFragment() {
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initViewAndData() {
        initList();
        initBanner();
        initAdapter();

    }

    public void initList() {
        SimpleModel simpleModel = new SimpleModel();
        simpleModel.setContent("nihao");
        simpleModel.setPicUrl("http://g.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cec52444bf9e45d688d53f2051.jpg");
        testList.clear();
        for (int i = 0; i < 6; i++) {
            testList.add(simpleModel);
        }
    }

    private void initBanner() {
        images.add(R.mipmap.banner01);
        images.add(R.mipmap.banner02);
        images.add(R.mipmap.banner03);
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImageLoader(new BannerImageLoader());
        banner.setViewPagerIsScroll(false);
        banner.update(images);
        banner.start();
    }

    private void initAdapter() {
        mRecyclerAdapter = new SimpleAdapter<SimpleModel>(mContext, testList, R.layout.item_recycler_normal) {
            @Override
            protected void onBindViewHolder(ViewHolder holder, SimpleModel data) {
                GlideUtils.loadImageView(mContext, data.getPicUrl(), holder.<ImageView>getView(R.id.rcv_iv_item));
                holder.<TextView>getView(R.id.rcv_tv_item).setText(data.getContent());
            }
        };
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 2, RecyclerView.VERTICAL, false);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mRecyclerAdapter);

    }

    @Override
    public void onStart() {
        banner.startAutoPlay();
        super.onStart();
    }

    @Override
    public void onPause() {
        banner.stopAutoPlay();
        super.onPause();
    }

}
