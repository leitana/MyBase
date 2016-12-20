package lx.base.apphall.canvas;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lx.base.apphall.R;

/**
 * 创建时间 2016/12/14
 * Created by linxiao.
 */

public class MyImageBtn extends LinearLayout {
    private ImageView mImgView = null;
    private TextView mTextView = null;
    private Context mContext;

    public MyImageBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.myimgbtn_layout, this, true);
        mContext = context;
        mImgView = (ImageView) findViewById(R.id.img);
        mTextView = (TextView) findViewById(R.id.text);
    }

    /*设置文字接口*/
    public void setText(String str) {
        mTextView.setText(str);
    }

    /*设置文字大小*/
    public void setTextSize(float size) {
        mTextView.setTextSize(size);
    }

    public void setImage(int res) {
        mImgView.setImageResource(res);
    }
}
