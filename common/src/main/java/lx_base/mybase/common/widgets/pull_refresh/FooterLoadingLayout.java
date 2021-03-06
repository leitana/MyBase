package lx_base.mybase.common.widgets.pull_refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import lx_base.mybase.R;


/**
 * 这个类封装了下拉刷新的布局
 *
 * @author Li Hong
 * @since 2013-7-30
 */
public class FooterLoadingLayout extends LoadingLayout {
    /**
     * 旋转动画时间
     */
    private static final int ROTATE_ANIM_DURATION = 150;
    private RelativeLayout mHeaderContainer;
    /**
     * 头部的Logo图片
     */
    private ImageView mlogoImage;
    /**
     * 箭头图片
     */
//    private ImageView mArrowImageView;
    /**
     * 进度条
     */
    private ProgressBar mProgressBar;
    /**
     * 状态提示TextView
     */
    private TextView mHintTextView;
    /**
     * 最后更新时间的TextView
     */
    private TextView mHeaderTimeView;
    /**
     * 最后更新时间的标题
     */
    private TextView mHeaderTimeViewTitle;
    private Animation mRotateDownAnim;

    /**
     * 构造方法
     *
     * @param context context
     */
    public FooterLoadingLayout(Context context) {
        super(context);
        init(context);
    }

    /**
     * 构造方法
     *
     * @param context context
     * @param attrs   attrs
     */
    public FooterLoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context context
     */
    private void init(Context context) {
        mHeaderContainer = (RelativeLayout) findViewById(R.id.pull_to_refresh_header_content);
        mlogoImage = (ImageView) findViewById(R.id.logo_image);
//        mArrowImageView = (ImageView) findViewById(R.id.pull_to_refresh_header_arrow);
        mHintTextView = (TextView) findViewById(R.id.pull_to_refresh_header_hint_textview);
        mProgressBar = (ProgressBar) findViewById(R.id.pull_to_refresh_header_progressbar);
        mHeaderTimeView = (TextView) findViewById(R.id.pull_to_refresh_header_time);
        mHeaderTimeViewTitle = (TextView) findViewById(R.id.pull_to_refresh_last_update_time_text);
        // 初始化旋转动画
//        float pivotValue = 0.5f;    // SUPPRESS CHECKSTYLE
//        float toDegree = -180f;     // SUPPRESS CHECKSTYLE
//        mRotateDownAnim = new RotateAnimation(0.0f, toDegree, Animation.RELATIVE_TO_SELF, pivotValue,
//                Animation.RELATIVE_TO_SELF, pivotValue);
//        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
//        mRotateDownAnim.setFillAfter(true);
        setState(State.RESET);

    }

    @Override
    protected View createLoadingView(Context context, AttributeSet attrs) {
        View container = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header, null);
        return container;
    }

    @Override
    public void setLastUpdatedLabel(CharSequence label) {
    }

    @Override
    public int getContentSize() {
        View view = findViewById(R.id.pull_to_load_footer_content);
        if (null != view) {
            return view.getHeight();
        }

        return (int) (getResources().getDisplayMetrics().density * 40);
    }

    @Override
    protected void onStateChanged(State curState, State oldState) {


        super.onStateChanged(curState, oldState);
    }

    @Override
    protected void onReset() {
//        mArrowImageView.clearAnimation();
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_normal);
//        mArrowImageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPullToRefresh() {
        if (State.RELEASE_TO_REFRESH == getPreState()) {
//            mArrowImageView.setVisibility(View.VISIBLE);
//            mArrowImageView.clearAnimation();
//            mArrowImageView.startAnimation(mRotateDownAnim);

        }

        mHintTextView.setText(R.string.pull_to_refresh_header_hint_normal2);


    }

    @Override
    protected void onReleaseToRefresh() {
//        mArrowImageView.clearAnimation();
//        mArrowImageView.startAnimation(mRotateDownAnim);
//        mArrowImageView.setVisibility(View.VISIBLE);
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_ready);

    }

    @Override
    protected void onRefreshing() {
//        mArrowImageView.clearAnimation();
//        mArrowImageView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_loading);

    }

    @Override
    protected void onNoMoreData() {
        mProgressBar.setVisibility(View.VISIBLE);
        mHintTextView.setText(R.string.pushmsg_center_no_more_msg);

    }

    @Override
    public void onPull(float scale) {
        float angle = scale * 180f; // SUPPRESS CHECK
//        mProgressBar.setVisibility(View.INVISIBLE);
//        mArrowImageView.setRotation(angle);
    }
}
