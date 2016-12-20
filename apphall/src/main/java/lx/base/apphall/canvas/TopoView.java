package lx.base.apphall.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;

import lx.base.apphall.R;

/**
 * 创建时间 2016/12/9
 * Created by linxiao.
 */

public class TopoView extends View {
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xff00ff, 0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    // 宽高
    private int mWidth, mHeight;
    private int index = 6;
    private TextPaint textPaint;

    public TopoView(Context context) {
        super(context);
        textPaint = new TextPaint();

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index + 1) % 9;
                postInvalidate();
            }
        });
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
        canvas.translate(mWidth / 2 - 64, mHeight / 2 - 64);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_computer);
        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
//        textPaint.setColor(mColors[0]);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(10f);
        textPaint.setTextSize(36.0f);
        // 这里的参数300，表示字符串的长度，当满300时，就会换行
        StaticLayout layout = new StaticLayout("62-114-派隶属西工厂在建（链）", textPaint, 300, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        canvas.translate(-64, 128);
        layout.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:

        }
        index = 1;
        return super.onTouchEvent(event);
    }
}
