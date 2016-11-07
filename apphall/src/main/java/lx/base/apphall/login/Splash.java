package lx.base.apphall.login;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx.base.apphall.viewpager_fragment.FragTest1;
import lx.base.apphall.viewpager_fragment.FragTest2;
import lx.base.apphall.viewpager_fragment.FragTest3;
import lx.base.apphall.viewpager_fragment.FragTest4;
import lx_base.mybase.common.base.BaseActionBarActivity;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2016/8/17.
 */
public class Splash extends BaseActionBarActivity {

    public static final int UPDATE_TEXT = 1;

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;

    private View mRootView;
    private Timer mTimer;
    private int currentItem; //当前页面

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.splash_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        hideActionBar();
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.addFragment(FragTest1.newInstance());
        myPagerAdapter.addFragment(FragTest2.newInstance());
        myPagerAdapter.addFragment(FragTest3.newInstance());
        myPagerAdapter.addFragment(FragTest4.newInstance());
        viewpager.setAdapter(myPagerAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mTimer != null) {
                    mTimer.cancel();
                }
                mTimer = new Timer();
                setTimerTask();
            }
        });
        indicator.setViewPager(viewpager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTimer = new Timer();
        setTimerTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    private void setTimerTask() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                currentItem = viewpager.getCurrentItem() + 1;
                message.what = UPDATE_TEXT;
                handler.sendMessage(message);
            }
        },2 * 1000, 2 * 1000);
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    if (currentItem < 4) {
                        viewpager.setCurrentItem(currentItem);
                    }

                    break;
                default:
                    break;
            }
        }
    };

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragment = new ArrayList<>();
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        public void addFragment(Fragment fragment) {
            mFragment.add(fragment);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }
    }


}
