package lx.base.apphall.design_pattern.bulidpattern;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 建造者模式
 * Created by 11300 on 2018/4/8.
 */

public class BuildPatternActivity extends BaseActionBarActivity {
    @BindView(R.id.tv_pattern)
    TextView tvPattern;

    private View mRootView;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.activity_text, null);
        ButterKnife.bind(this, mRootView);
        Person.Builder builder = new Person.Builder();
        Person person = builder
                .age(11)
                .name("张三")
                .height(175)
                .build();
        tvPattern.setText(person.toString());
        return mRootView;
    }

}
