package lx.base.apphall.viewgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lx.base.apphall.R;
import lx.base.apphall.canvas.MyImageBtn;
import lx.base.apphall.recyclierview.Coordinate;

/**
 * 创建时间 2016/12/14
 * Created by linxiao.
 */

public class TestViewGroup2 extends ViewGroup {
    private static final String TAG = "testviewgroup";
    private List<MyImageBtn> mTopo = new ArrayList<>();
    private Context mContext;
    private int mWidth;//容器的宽
    private int mHeight;//容器的高
    private int mMinSize;//宽和高中最小值
    private List<String> stringList;
    private int count;
    private List<Coordinate> xyList = new ArrayList<>();
    private int childWidth;
    private int childHeight;
    private List<Coordinate> lineList = new ArrayList<>();
    private int test = 7;

    public TestViewGroup2(Context context) {
        super(context);
        mContext = context;
        setWillNotDraw(false);
    }

    public TestViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
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
        int cx = mWidth / 2;//容器中心x坐标
        int cy = mHeight / 2;//容器中心y坐标
        View firstView = getChildAt(1);//第一个子节点大小
        childWidth = firstView.getMeasuredWidth() + 10;
        childHeight = firstView.getMeasuredHeight() + 10;
        int widthNum = mWidth / childWidth - 2;//一行最大数量
        int heighNum = mHeight / childHeight - 3;//一列最大数量

        for (int x = 1; x <= widthNum; x++) {
            for (int y = 1; y <= heighNum; y++) {
                if (x != widthNum / 2 && y != heighNum / 2 ||
                        x != widthNum / 2 && y != heighNum / 2 + 1 ||
                        x != widthNum / 2 && y != heighNum / 2 - 1 ||
                        x != widthNum / 2 - 1 && y != heighNum / 2 ||
                        x != widthNum / 2 + 1 && y != heighNum / 2 ||
                        x != widthNum / 2 - 1 && y != heighNum / 2 + 1 ||
                        x != widthNum / 2 - 1 && y != heighNum / 2 - 1 ||
                        x != widthNum / 2 + 1 && y != heighNum / 2 - 1 ||
                        x != widthNum / 2 + 1 && y != heighNum / 2 + 1) {
                    Coordinate coordinate = new Coordinate();
                    coordinate.setxPosition(x);
                    coordinate.setyPosition(y);
                    xyList.add(coordinate);
                }
            }
        }

        //初始坐标
        int left = 10;
        int top = 10;
        int right = left + childWidth;
        int bottom = top + childHeight;

        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);
            if (i == 0) {//中心
                childView.layout((getWidth() - childView.getMeasuredWidth()) / 2, (getHeight() - childView.getMeasuredHeight()) / 2,
                        (getWidth() + childView.getMeasuredWidth()) / 2, (getHeight() + childView.getMeasuredHeight()) / 2);
                int centerLeft = (getWidth() - childView.getMeasuredWidth()) / 2;
                int centerTop = (getHeight() - childView.getMeasuredHeight()) / 2;
                int centerRight = (getWidth() + childView.getMeasuredWidth()) / 2;
                int centerBottom = (getHeight() + childView.getMeasuredHeight()) / 2;
                childView.layout(centerLeft, centerTop, centerRight, centerBottom);
//                Coordinate center = new Coordinate();
//                center.setxPosition((centerLeft + centerRight) / 2);
//                center.setyPosition((centerTop + centerBottom) / 2);
//                lineList.add(center);
                for (int p = 0; p < xyList.size(); p++) {
                    if (Math.abs(50 + childWidth * xyList.get(p).getxPosition() - centerLeft) < childWidth + 10) {
                        xyList.remove(p);
                    }
                }
                for (int p = 0; p < xyList.size(); p++) {
                    if (Math.abs(50 + childWidth * xyList.get(p).getyPosition() - right) < childHeight + 10) {
                        xyList.remove(p);
                    }
                }
            } else {
                if (!changed) {
                    int random = (int) (Math.random() * (xyList.size() - 1));//随机数
                    left = 10 + childWidth * xyList.get(random).getxPosition();
                    top = 10 + childHeight * xyList.get(random).getyPosition();
                    right = left + childWidth + 15;
                    bottom = top + childHeight;
                    childView.layout(left, top, right, bottom);//l,t,r,b
//                    for (int p = 0; p < xyList.size(); p++) {
//                        if (Math.abs(50 + childWidth * xyList.get(p).getxPosition() - left) < childWidth - 10) {
//                            xyList.remove(p);
//                        }
//                    }
//                    for (int p = 0; p < xyList.size(); p++) {
//                        if (Math.abs(50 + childWidth * xyList.get(p).getyPosition() - top) < childHeight - 10) {
//                            xyList.remove(p);
//                        }
//                    }
                    xyList.remove(random);
                    Coordinate center = new Coordinate();
                    center.setxPosition((left + right) / 2);
                    center.setyPosition((top + bottom) / 2);
                    lineList.add(center);
                }
//                angle += grad;
            }
            left = 50;
            top = 50;
            right = left + childWidth;
            bottom = top + childHeight;
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
        int cx = mWidth / 2;//容器中心x坐标
        int cy = mHeight / 2;//容器中心y坐标

        for (int i = 0; i < lineList.size(); i++) {
            canvas.drawLine(lineList.get(i).getxPosition(), lineList.get(i).getyPosition(), cx, cy, p);
        }
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
