package lx_base.mybase.common.base;

import android.os.Build;
import android.view.View;
import android.widget.RelativeLayout;

import lx_base.mybase.R;
import lx_base.mybase.common.util.IdUtil;

/**
 * 带ActionBar的基础父类
 * Created by Administrator on 2016/7/5.
 */
public abstract class BaseUIActivity extends BaseActivity{
    //rootview
    private RelativeLayout relativeLayoutRoot;
    //actionbar view
    private View actionBarView;
    //content view
    private View contentView;
    @Override
    protected void initParm() {
        super.initParm();
    }

    @Override
    protected void initView() {
        super.initView();

        setContentView(R.layout.common_activity_actionbar);
        relativeLayoutRoot = (RelativeLayout) findViewById(R.id.relativeLayout_root);

        addMyActionBarView(setMyActionBarView());

        addMyContentView(setMyContentView());
    }

    public RelativeLayout getMyRootView() {
        return relativeLayoutRoot;
    }

    public View getMyActionBar() {
        return actionBarView;
    }

    protected abstract View setMyActionBarView();

    protected abstract View setMyContentView();

    /**
     * 将actionbar view 加入 root view
     * @param view
     */
    private void addMyActionBarView(View view) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.
                LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        if (relativeLayoutRoot != null && view != null){
            actionBarView = view;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                view.setId(IdUtil.generateViewId());
            } else {
                view.setId(View.generateViewId());
            }
            relativeLayoutRoot.addView(actionBarView, layoutParams);
        }
    }

    /**
     * 将 content view 加入到 root view
     * @param view
     */
    private void addMyContentView(View view) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.
                LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        if (actionBarView == null) { //no actionBar view
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        } else {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.BELOW, actionBarView.getId());
        }

        if (relativeLayoutRoot != null && view != null) {
            contentView = view;
            relativeLayoutRoot.addView(contentView, layoutParams);
        }
    }
    public   void  hideActionBar(){
        actionBarView.setVisibility(View.GONE);
    }
}
