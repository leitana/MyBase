package lx.base.apphall.canvas;

import android.view.View;
import android.widget.LinearLayout;

import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/12/9
 * Created by linxiao.
 */

public class TopoActivity extends BaseActionBarActivity {
    private View mRootView;
    private LinearLayout layout;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.empty, null);
        layout = (LinearLayout) mRootView.findViewById(R.id.root);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        TopoView topoView = new TopoView(this);
        topoView.invalidate();
        layout.addView(topoView);
    }
}
