package lx.base.apphall.custom_view;

import android.view.View;

import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/9/20
 * Created by linxiao.
 */
public class ViewActivity extends BaseActionBarActivity{
    private View mRootView;
    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.first_view, null);
        return mRootView;
    }
}
