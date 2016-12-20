package lx.base.apphall.viewgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lx.base.apphall.R;
import lx.base.apphall.canvas.MyImageBtn;

/**
 * 创建时间 2016/12/14
 * Created by linxiao.
 */

public class TestViewGroup1 extends ViewGroup {
    private static final String TAG = "testviewgroup";
    private List<MyImageBtn> mTopo = new ArrayList<>();
    private Context mContext;
    private int mWidth;//容器的宽
    private int mHeight;//容器的高
    private int mMinSize;//宽和高中最小值
    private List<String> stringList;
    private int count;

    public TestViewGroup1(Context context) {
        super(context);
        mContext = context;
        setWillNotDraw(false);
    }

    public TestViewGroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //对所有子view进行测量，触发所有子view的onMeasure函数
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //宽度模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //测量宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //高度模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //测量高度
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //子view数目
        mWidth = widthSize;
        mHeight = heightSize;
        int minSize = Math.min(widthSize, heightSize);
        //取最小值当作显示区域
        mMinSize = minSize;
        int childCount = getChildCount();
        if (childCount == 0) {
            //如果当前没有子view,就没有存在的意义，无需占空间
            setMeasuredDimension(0, 0);
        } else {
            setMeasuredDimension(widthSize, heightSize);
        }
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
//        int radius = getWidth() / 2 - padding ; // 减去padding的半径
//        int realRadius = getWidth() / 2;  // 实际半径
        double angle = 0;
        double grad = Math.PI * 2 / (childCount - 1);//梯度，每个TextView之间的角度 (Math.PI 是数学中的90°)
        double rightAngle = Math.PI / 2;
        int cx = mWidth / 2;//容器中心x坐标
        int cy = mHeight / 2;//容器中心y坐标
        int radius = mMinSize / 2 / 2 / 2 + mMinSize / 2 / 2;//动态气泡的组成圆的半径

        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);
            if (i == 0) {//中心
                childView.layout((getWidth() - childView.getMeasuredWidth()) / 2, (getHeight() - childView.getMeasuredHeight()) / 2,
                        (getWidth() + childView.getMeasuredWidth()) / 2, (getHeight() + childView.getMeasuredHeight()) / 2);
            } else {
                int left = 0;
                int top = 0;
                int right = 0;
                int bottom = 0;
                int x = 0, y = 0;
                int childRadius = childView.getMeasuredWidth() / 2;//计算得来//固定死的mMinSize / 6 / 2;//气泡半径
                if (angle >= 0 && angle < rightAngle) {  //0 - 90
                    //保持角度在 0 - 90
                    x = (int) (radius * Math.sin(Math.abs(angle % rightAngle)));
                    y = (int) (radius * Math.cos(Math.abs(angle % rightAngle)));

                    left = cx + x - childRadius;
                    top = cy - y - childRadius;
                    right = left + 2 * childRadius;
                    bottom = top + 2 * childRadius;

                } else if (angle >= rightAngle && angle < rightAngle * 2) { // 90 - 180
                    x = (int) (radius * Math.sin(Math.abs(angle % rightAngle)));
                    y = (int) (radius * Math.cos(Math.abs(angle % rightAngle)));
                    left = cx + y - childRadius;
                    top = cy + x - childRadius;
                    right = left + 2 * childRadius;
                    bottom = top + 2 * childRadius;
                    //System.out.println("-----------90 - 180---1--a:"+a);
                    //System.out.println("-----------90 - 180---1--b:"+b);

                } else if (angle >= rightAngle * 2 && angle < rightAngle * 3) { // 180 - 270
                    x = (int) (radius * Math.sin(Math.abs(angle % rightAngle)));
                    y = (int) (radius * Math.cos(Math.abs(angle % rightAngle)));
                    left = cx - x - childRadius;
                    top = cy + y - childRadius;
                    right = left + 2 * childRadius;
                    bottom = top + 2 * childRadius;
                    //System.out.println("-----------180 - 270---1--a:"+a);
                    //System.out.println("-----------180 - 270---1--b:"+b);
                } else if (angle >= rightAngle * 3 && angle < rightAngle * 4) { //270 - 360
                    x = (int) (radius * Math.sin(Math.abs(angle % rightAngle)));
                    y = (int) (radius * Math.cos(Math.abs(angle % rightAngle)));
                    left = cx - y - childRadius;
                    top = cy - x - childRadius;
                    right = left + 2 * childRadius;
                    bottom = top + 2 * childRadius;
                    //System.out.println("-----------270 - 360---1--a:"+a);
                    // System.out.println("-----------270 - 360---1--b:"+b);
                }
                childView.layout(left, top, right, bottom + 100);//l,t,r,b
                angle += grad;
//                float angle = (float)((360.0f / (childCount - 1) * (i - 1)) / 180 * Math.PI);
//                int startX = getXByAngle(radius , angle , childView);
//                int startY = getYByAngle(radius , angle , childView);
//                childView.layout(realRadius + startX, realRadius + startY,
//                        realRadius + startX + childView.getMeasuredWidth(), realRadius + startY + childView.getMeasuredHeight());

            }
            final int finalI = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, stringList.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.black));
//        p.setStrokeWidth(10f);
        double angle = 0;
        double grad = Math.PI * 2 / (count - 1);//梯度，每个TextView之间的角度 (Math.PI 是数学中的90°)
        double rightAngle = Math.PI / 2;
        int cx = mWidth / 2;//容器中心x坐标
        int cy = mHeight / 2;//容器中心y坐标
        int radius = mMinSize / 2 / 2 / 2 + mMinSize / 2 / 2;//动态气泡的组成圆的半径

        for (int i = 0; i < count; i++) {
            final View childView = getChildAt(i);
            if (i == 0) {//中心
//                childView.layout((getWidth() - childView.getMeasuredWidth()) / 2, (getHeight() - childView.getMeasuredHeight()) / 2,
//                        (getWidth() + childView.getMeasuredWidth()) / 2, (getHeight() + childView.getMeasuredHeight()) / 2);
            } else {
                int left = 0;
                int top = 0;
                int right = 0;
                int bottom = 0;
                int x = 0, y = 0;
                int childRadius = childView.getMeasuredWidth() / 2;//计算得来//固定死的mMinSize / 6 / 2;//气泡半径
                if (angle >= 0 && angle < rightAngle) {  //0 - 90
                    //保持角度在 0 - 90
                    x = (int) (radius * Math.sin(Math.abs(angle % rightAngle)));
                    y = (int) (radius * Math.cos(Math.abs(angle % rightAngle)));

                    left = cx + x - childRadius;
                    top = cy - y - childRadius;
                    right = left + 2 * childRadius;
                    bottom = top + 2 * childRadius;

                } else if (angle >= rightAngle && angle < rightAngle * 2) { // 90 - 180
                    x = (int) (radius * Math.sin(Math.abs(angle % rightAngle)));
                    y = (int) (radius * Math.cos(Math.abs(angle % rightAngle)));
                    left = cx + y - childRadius;
                    top = cy + x - childRadius;
                    right = left + 2 * childRadius;
                    bottom = top + 2 * childRadius;
                    //System.out.println("-----------90 - 180---1--a:"+a);
                    //System.out.println("-----------90 - 180---1--b:"+b);

                } else if (angle >= rightAngle * 2 && angle < rightAngle * 3) { // 180 - 270
                    x = (int) (radius * Math.sin(Math.abs(angle % rightAngle)));
                    y = (int) (radius * Math.cos(Math.abs(angle % rightAngle)));
                    left = cx - x - childRadius;
                    top = cy + y - childRadius;
                    right = left + 2 * childRadius;
                    bottom = top + 2 * childRadius;
                    //System.out.println("-----------180 - 270---1--a:"+a);
                    //System.out.println("-----------180 - 270---1--b:"+b);
                } else if (angle >= rightAngle * 3 && angle < rightAngle * 4) { //270 - 360
                    x = (int) (radius * Math.sin(Math.abs(angle % rightAngle)));
                    y = (int) (radius * Math.cos(Math.abs(angle % rightAngle)));
                    left = cx - y - childRadius;
                    top = cy - x - childRadius;
                    right = left + 2 * childRadius;
                    bottom = top + 2 * childRadius;
                    //System.out.println("-----------270 - 360---1--a:"+a);
                    // System.out.println("-----------270 - 360---1--b:"+b);
                }
//                childView.layout(left, top, right, bottom + 100);//l,t,r,b
                canvas.drawLine((left + right) / 2, (top + bottom) / 2, cx, cy, p);
                angle += grad;
//                float angle = (float)((360.0f / (childCount - 1) * (i - 1)) / 180 * Math.PI);
//                int startX = getXByAngle(radius , angle , childView);
//                int startY = getYByAngle(radius , angle , childView);
//                childView.layout(realRadius + startX, realRadius + startY,
//                        realRadius + startX + childView.getMeasuredWidth(), realRadius + startY + childView.getMeasuredHeight());

            }
        }
    }

    private int getYByAngle(float radius, float angle, View view) {
        float y = (float) (radius * Math.cos(angle));
        Log.i(TAG, "getYByAngle  radius = " + radius + "angle = " + angle + "y = " + y);
        return (int) y - view.getMeasuredHeight() / 2;
    }

    private int getXByAngle(float radius, float angle, View view) {
        float x = (float) (radius * Math.sin(angle));
        Log.i(TAG, "getXByAngle  radius = " + radius + "angle = " + angle + "x = " + x);
        return (int) x - view.getMeasuredWidth() / 2;
    }

    public void addChildViews(List<MyImageBtn> myImageBtn) {
        stringList = new ArrayList<>();
        if (myImageBtn == null)
            return;
        count = myImageBtn.size();
        for (int i = 0; i < myImageBtn.size(); i++) {
            addView(myImageBtn.get(i));
            stringList.add(i + "");
        }
    }
}
