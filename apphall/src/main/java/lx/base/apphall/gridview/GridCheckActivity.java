package lx.base.apphall.gridview;

import android.view.View;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/6/14.
 */

public class GridCheckActivity extends BaseActionBarActivity {
    @BindView(R.id.flowlayout)
    TagFlowLayout mFlowLayout;
    private View mRootView;
    private List<String> mVals;
    private String[] mVals2 = {"111", "2222"};

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.activity_grid_check, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        mVals = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            mVals.add("Tag:" + i);
        }
        mFlowLayout.setAdapter(new TagAdapter<String>(mVals2)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.item_text,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
    }
}
