package lx.base.apphall.canvas;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lx.base.apphall.R;
import lx.base.apphall.viewgroup.TestViewGroupActivity1;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/12/8
 * Created by linxiao.
 */

public class CanvasActivity extends BaseActionBarActivity {
    @BindView(R.id.pie)
    TextView pie;
    @BindView(R.id.translate)
    TextView translate;
    @BindView(R.id.topo)
    TextView topo;
    @BindView(R.id.test)
    TextView test;
    @BindView(R.id.myimage)
    MyImageBtn myimage;
    private View mRootView;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.canvas_activity, null);
        ButterKnife.bind(this, mRootView);
        myimage.setText("测试-1测试-1测试-1测试-1测试-1");
        return mRootView;
    }


    @OnClick({R.id.pie, R.id.translate, R.id.topo, R.id.test, R.id.myimage})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.pie:
                intent.setClass(CanvasActivity.this, PieActivity.class);
                startActivity(intent);
                break;
            case R.id.translate:
                intent.setClass(CanvasActivity.this, TranslateActivity.class);
                startActivity(intent);
                break;
            case R.id.topo:
                intent.setClass(CanvasActivity.this, TopoActivity.class);
                startActivity(intent);
                break;
            case R.id.test:
                intent.setClass(CanvasActivity.this, TestViewGroupActivity1.class);
                startActivity(intent);
                break;
        }
    }

}
