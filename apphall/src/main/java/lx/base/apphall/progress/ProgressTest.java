package lx.base.apphall.progress;

import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/7/28.
 */

public class ProgressTest extends BaseActionBarActivity{
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private View mRootView;
    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.activity_progress, null);
        ButterKnife.bind(this, mRootView);

        progressBar.setSecondaryProgress(80);
        progressBar.setProgress(60);

        return mRootView;
    }

}
