package lx.base.apphall.design_pattern.factory_pattern;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2018/4/9.
 */

public class FactoryActivity extends BaseActionBarActivity{
    @BindView(R.id.tv_pattern)
    TextView tvPattern;

    private View mRootView;
    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.activity_text, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getClass(Circle.class);
        tvPattern.setText(shape.draw());
    }
}
