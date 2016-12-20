package lx.base.apphall.viewgroup;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lx.base.apphall.R;
import lx.base.apphall.canvas.MyImageBtn;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/12/15
 * Created by linxiao.
 */

public class TestViewGroupActivity1 extends BaseActionBarActivity {
    private View mRootView;
    private LinearLayout layout;
    private List<MyImageBtn> myImageBtns;
    private Context mContext = TestViewGroupActivity1.this;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.empty, null);
        myImageBtns = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MyImageBtn myImageBtn = new MyImageBtn(mContext, null);
            myImageBtn.setText("测试" + i);
            if (i == 0) {
                myImageBtn.setImage(R.mipmap.ic_red_com);
            } else {
                myImageBtn.setImage(R.mipmap.ic_yellow_com);
            }
            myImageBtns.add(myImageBtn);
        }
        layout = (LinearLayout) mRootView.findViewById(R.id.root);
        TestViewGroup1 viewGroup1 = new TestViewGroup1(TestViewGroupActivity1.this);
        layout.addView(viewGroup1);
        viewGroup1.addChildViews(myImageBtns);
        return mRootView;
    }
}
