package lx.base.apphall.ExpandableListView;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lx.base.apphall.ExpandableListView.ThreeExpandableListView.ThreeExpandableListView;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/6/2.
 */

public class ExpandableMain extends BaseActionBarActivity {
    @BindView(R.id.dynamic)
    TextView dynamic;
    @BindView(R.id.three)
    TextView three;

    private Context mContext = ExpandableMain.this;
    private View mRootView;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.activity_expandable_main, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }


    @OnClick({R.id.dynamic, R.id.three})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.dynamic:
                intent.setClass(mContext, DynamicAddView.class);
                startActivity(intent);
                break;
            case R.id.three:
                intent.setClass(mContext, ThreeExpandableListView.class);
                startActivity(intent);
                break;
        }
    }
}
