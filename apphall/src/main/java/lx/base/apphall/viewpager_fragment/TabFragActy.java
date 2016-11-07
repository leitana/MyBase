package lx.base.apphall.viewpager_fragment;

import android.support.design.widget.TabLayout;
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
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by Administrator on 2016/8/8.
 */
public class TabFragActy extends BaseActionBarActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private View mRootView;
    private List<String> mTitleList = new ArrayList<String>();//页卡标题集合
    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.tabfrag_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        mTitleList.add("头条");
        mTitleList.add("体育");
        mTitleList.add("笑话");
        tabs.addTab(tabs.newTab().setText(mTitleList.get(0)));
        tabs.addTab(tabs.newTab().setText(mTitleList.get(1)));
        tabs.addTab(tabs.newTab().setText(mTitleList.get(2)));
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.addFragment(FragTest1.newInstance(), mTitleList.get(0));
        myPagerAdapter.addFragment(FragTest2.newInstance(), mTitleList.get(1));
        myPagerAdapter.addFragment(FragTest3.newInstance(), mTitleList.get(2));
        viewpager.setAdapter(myPagerAdapter);
        tabs.setupWithViewPager(viewpager);
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragment = new ArrayList<>();
        private List<String> mTitle = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragment.add(fragment);
            mTitle.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
    }
}
