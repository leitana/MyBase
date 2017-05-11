package lx.base.apphall.event_bus.event1;

import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/5/5.
 */

public class EventSecondActivity extends BaseActionBarActivity {
    @BindView(R.id.bt_send)
    Button btSend;
    private View mRootView;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.event_second_activity, null);
        ButterKnife.bind(this,mRootView);
        return mRootView;
    }

    @OnClick(R.id.bt_send)
    public void onViewClicked() {
        EventBus.getDefault().post(new FirstEvent("FirstEvent Btn Clicked"));
    }
}
