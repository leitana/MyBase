package lx.base.apphall.gridview;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by Administrator on 2016/7/18.
 */
public class GridActivity extends BaseActionBarActivity {
    private View mRootView;
    private GridView mGridView;
    private List<String> titles;
    private GridViewAdapter mAdapter;
    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.grid_activity, null);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        setMyActionBarTitle("动态GridView");
        setMyRightTextView2("说明");
        showMyRightTextView2();
        titles = new ArrayList<>();
        mGridView = (GridView) findViewById(R.id.gv_list);
        initData();
        mAdapter = new GridViewAdapter(this,titles);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == titles.size()) {
                    titles.add((titles.size() + 1)+"功能");
                    showToast("您添加了" + titles.size() + 1);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }



    private void initData() {
        for (int i = 1; i < 9; i++) {
            titles.add(i+"功能");
        }
    }

    @Override
    public void clickMyRightTextView2() {
        super.clickMyRightTextView2();
        showPopupWindow(getMyRightTextView2());
    }
    private void showPopupWindow(View view) {
        View contentView = getLayoutInflater().inflate(R.layout.popwindow_layout, null);
        TextView textView = (TextView) contentView.findViewById(R.id.tv_pop);
        textView.setText("gridview");
        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bottom_shadow_bg));
        popupWindow.showAsDropDown(view);
    }
}