package lx.base.apphall.canvas;

import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/12/9
 * Created by linxiao.
 */

public class PieActivity extends BaseActionBarActivity {
    private View mRootView;
    private LinearLayout layout;
    private ArrayList<PieData> mDataList;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.empty, null);
        layout = (LinearLayout) mRootView.findViewById(R.id.root);
        init();
        return mRootView;
    }

    private void init() {
//        DrawView view = new DrawView(this);
//        view.setMinimumHeight(500);
//        view.setMinimumWidth(300);
        mDataList = new ArrayList<PieData>();
        PieView view = new PieView(this);
        for (int i = 0; i < 8; i++) {
            PieData mData = new PieData("一" + i, 10);
            mDataList.add(mData);
        }
        view.setData(mDataList);
        //通知view组件重绘
        view.invalidate();
        layout.addView(view);
    }
}
