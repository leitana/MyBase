package lx.base.apphall.event_bus.event1;

import android.view.View;

import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/5/5.
 */

public class EventFirstActvity extends BaseActionBarActivity{
    private View mRootView;
    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.event_first_activity, null);
        return mRootView;
    }

    @Override
    protected void initParm() {
        super.initParm();
    }

    @Override
    protected void initView() {
        super.initView();
    }
}
