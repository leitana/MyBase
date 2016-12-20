package lx.base.apphall.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import lx.base.apphall.R;

/**
 * 创建时间 2016/12/9
 * Created by linxiao.
 */

public class TranslateView extends View {
    // 宽高
    private int mWidth, mHeight;

    public TranslateView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.little_blue));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);

        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rectF = new RectF(-400, -400, 400, 400);

        for (int i = 0; i < 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rectF, paint);
        }
    }
}
