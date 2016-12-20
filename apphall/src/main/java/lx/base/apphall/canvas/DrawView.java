package lx.base.apphall.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import lx.base.apphall.R;

/**
 * 创建时间 2016/12/8
 * Created by linxiao.
 */

public class DrawView extends View {
    public DrawView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 方法 说明 drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
         * drawLine 绘制直线 drawPoin 绘制点
         */
        //创建画笔
        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.little_blue));
        p.setTextSize(32);
        canvas.drawText("画圆", 20, 32, p);
        canvas.drawCircle(200, 120, 40, p);

        //画点
        p.setStrokeWidth(10);
        p.setStyle(Paint.Style.FILL);
        canvas.drawPoint(200, 240, p);
        canvas.drawPoints(new float[]{200, 250, 200, 260, 200, 270}, p);

        //画线
        canvas.drawLine(110, 40, 190, 90, p);
    }
}
