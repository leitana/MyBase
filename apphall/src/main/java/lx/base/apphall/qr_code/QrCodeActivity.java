package lx.base.apphall.qr_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/10/20
 * Created by linxiao.
 */

public class QrCodeActivity extends BaseActionBarActivity {
    public static final int REQUEST_CODE = 1;
    @BindView(R.id.bt_qr)
    Button btQr;
    private View mRootView;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.qrcode_activity, null);
        ButterKnife.bind(this,mRootView);
        ZXingLibrary.initDisplayOpinion(this);
        return mRootView;
    }


    @OnClick(R.id.bt_qr)
    public void onClick() {
        Intent intent = new Intent(QrCodeActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(QrCodeActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
