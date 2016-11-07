package lx.base.apphall.viewpager_fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx.base.apphall.widgets.ShadeTabView;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by Administrator on 2016/8/9.
 */
public class BottomFragActy extends BaseActionBarActivity implements View.OnClickListener{
    @BindView(R.id.id_indicator_home)
    ShadeTabView home;
    @BindView(R.id.id_indicator_message)
    ShadeTabView message;
    @BindView(R.id.id_indicator_guide)
    ShadeTabView search;
    @BindView(R.id.id_indicator_user)
    ShadeTabView user;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private View mRootView;
    private MyPageAdapter myPageAdapter;
    private List<ShadeTabView> mTabIndicator = new ArrayList<ShadeTabView>();

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.fragment_bottom_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        myPageAdapter = new MyPageAdapter(getSupportFragmentManager());
        mTabIndicator.add(home);
        mTabIndicator.add(message);
        mTabIndicator.add(search);
        mTabIndicator.add(user);
        home.setOnClickListener(this);
        message.setOnClickListener(this);
        search.setOnClickListener(this);
        user.setOnClickListener(this);
        mTabIndicator.get(0).setTabView(1.0f);

        viewpager.setAdapter(myPageAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changeTab();
            }

            @Override
            public void onPageSelected(int position) {
                changeTab();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicator.size(); i++) {
            mTabIndicator.get(i).setTabView(0);
        }
    }

    @Override
    public void onClick(View v) {
        resetOtherTabs();
        switch (v.getId()) {
            case R.id.id_indicator_home:
                mTabIndicator.get(0).setTabView(1.0f);
                viewpager.setCurrentItem(0);
                break;
            case R.id.id_indicator_message:
                mTabIndicator.get(1).setTabView(1.0f);
                viewpager.setCurrentItem(1);
                break;
            case R.id.id_indicator_guide:
                mTabIndicator.get(2).setTabView(1.0f);
                viewpager.setCurrentItem(2);
                break;
            case R.id.id_indicator_user:
                mTabIndicator.get(3).setTabView(1.0f);
                viewpager.setCurrentItem(3);
                break;
        }
    }


    private void changeTab() {
        resetOtherTabs();
        switch (viewpager.getCurrentItem()) {
            case 0:
                mTabIndicator.get(0).setTabView(1.0f);
                break;
            case 1:
                mTabIndicator.get(1).setTabView(1.0f);
                break;
            case 2:
                mTabIndicator.get(2).setTabView(1.0f);
                break;
            case 3:
                mTabIndicator.get(3).setTabView(1.0f);
                break;
            default:
                mTabIndicator.get(0).setTabView(1.0f);
        }
    }

    private class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FragTest1.newInstance();
                case 1:
                    return FragTest2.newInstance();
                case 2:
                    return FragTest3.newInstance();
                case 3:
                    return FragTest4.newInstance();
                default:
                    return FragTest1.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
