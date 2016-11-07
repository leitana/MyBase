package lx.base.apphall.viewpager_fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by Administrator on 2016/8/8.
 */
public class FragActivity extends BaseActionBarActivity {
    @BindView(R.id.tv_tablayout)
    TextView tvTablayout;
    @BindView(R.id.tv_diy)
    TextView tvDiy;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    private View mRootView;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.viewpager_frag_activity, null);
        ButterKnife.bind(this,mRootView);
        return mRootView;
    }


    @OnClick({R.id.tv_tablayout, R.id.tv_diy, R.id.tv_bottom})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_tablayout:
                intent.setClass(FragActivity.this, TabFragActy.class);
                startActivity(intent);
                break;
            case R.id.tv_diy:
                intent.setClass(FragActivity.this, DiyTabActy.class);
                startActivity(intent);
                break;
            case R.id.tv_bottom:
                intent.setClass(FragActivity.this, BottomFragActy.class);
                startActivity(intent);
                break;
        }
    }
}
