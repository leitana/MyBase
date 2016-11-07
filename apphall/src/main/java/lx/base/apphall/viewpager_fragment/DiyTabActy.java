package lx.base.apphall.viewpager_fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by Administrator on 2016/8/9.
 */
public class DiyTabActy extends BaseActionBarActivity {
    @BindView(R.id.tab_web)
    TextView tab_web;
    @BindView(R.id.tab_video)
    TextView tab_video;
    @BindView(R.id.tab_game)
    TextView tab_game;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private View mRootVIew;
    private MyPageAdapter myPageAdapter;

    @Override
    protected View setMyContentView() {
        mRootVIew = getLayoutInflater().inflate(R.layout.diy_tab_ativity, null);
        ButterKnife.bind(this, mRootVIew);
        return mRootVIew;
    }

    @Override
    protected void initView() {
        super.initView();
        myPageAdapter = new MyPageAdapter(getSupportFragmentManager());
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
        tab_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(0);
            }
        });
        tab_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(1);
            }
        });
        tab_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(2);
            }
        });
    }

    private class MyPageAdapter extends FragmentStatePagerAdapter {
        private final String[] TITLES = {"网页", "视频", "游戏"};

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
                default:
                    return FragTest1.newInstance();
            }
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
    private void changeTab() {
        if (viewpager.getCurrentItem() == 0) {
            tab_web.setTextColor(getResources().getColor(R.color.lite_blue));
            tab_video.setTextColor(getResources().getColor(R.color.black));
            tab_game.setTextColor(getResources().getColor(R.color.black));
            return;
        } else if (viewpager.getCurrentItem() == 1) {
            tab_video.setTextColor(getResources().getColor(R.color.lite_blue));
            tab_web.setTextColor(getResources().getColor(R.color.black));
            tab_game.setTextColor(getResources().getColor(R.color.black));
            return;
        } else if (viewpager.getCurrentItem() == 2) {
            tab_game.setTextColor(getResources().getColor(R.color.lite_blue));
            tab_web.setTextColor(getResources().getColor(R.color.black));
            tab_video.setTextColor(getResources().getColor(R.color.black));
            return;
        } else {
            return;
        }
    }
}
