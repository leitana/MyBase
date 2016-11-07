package lx.base.apphall.recyclierview;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/10/27
 * Created by linxiao.
 */

public class RecyclerMainActivity extends BaseActionBarActivity {
    @BindView(R.id.simple)
    TextView simple;
    @BindView(R.id.test)
    TextView test;
    private View mRootView;

    @OnClick(R.id.simple)
    public void onClicksimple() {
        Intent intent = new Intent(this, RecyclerListActivity.class);
        startActivity(intent);
    }

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.recycler_main_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

}
