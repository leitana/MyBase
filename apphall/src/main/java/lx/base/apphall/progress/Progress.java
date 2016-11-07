package lx.base.apphall.progress;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/9/27
 * Created by linxiao.
 */

public class Progress extends BaseActionBarActivity {
    @BindView(R.id.load)
    ImageView load;
    private View mRootView;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.progress_layout, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        load.setBackgroundResource(R.drawable.commonui_spinner);
        AnimationDrawable anim = (AnimationDrawable) load.getBackground();
        anim.start();
    }

}
