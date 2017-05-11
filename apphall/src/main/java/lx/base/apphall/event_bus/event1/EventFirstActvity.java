package lx.base.apphall.event_bus.event1;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/5/5.
 */

public class EventFirstActvity extends BaseActionBarActivity {
    @BindView(R.id.bt_try)
    Button btTry;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    private View mRootView;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.event_first_activity, null);
        ButterKnife.bind(this,mRootView);
        Logger.init();
        return mRootView;
    }

    @Override
    protected void initParm() {
        super.initParm();
        //注册EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        super.initView();
    }
    @OnClick(R.id.bt_try)
    public void onViewClicked() {
        Intent intent = new Intent(EventFirstActvity.this, EventSecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销EventBus
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onEventMainThread(FirstEvent firstEvent) {
        String msg = "FirstActivty get the message:" + firstEvent.getMsg();
        Logger.d(msg);
        tvInfo.setText(msg);
    }
}
