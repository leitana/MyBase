package lx.base.apphall.design_pattern.strategy_pattern;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 策略模式
 * Created by 11300 on 2018/4/8.
 */

public class StrategyActivity extends BaseActionBarActivity{
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
        TravelContext travelContext = new TravelContext();
        travelContext.setStrategy(new PlaneStrategy());
        travelContext.travel();
        travelContext.setStrategy(new WalkStrategy());
        travelContext.travel();
        travelContext.setStrategy(new SubwayStrategy());
        travelContext.travel();
        tvPattern.setText(travelContext.travel());
    }
}
