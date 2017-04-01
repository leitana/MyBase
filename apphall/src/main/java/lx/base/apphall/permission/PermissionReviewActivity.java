package lx.base.apphall.permission;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;
import lx_base.mybase.common.util.PermissionsChecker;

/**
 * Created by 11300 on 2017/3/30.
 */

public class PermissionReviewActivity extends BaseActionBarActivity{
    private View mRootView;
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_SMS
    };
    private PermissionsChecker mPermissionsChecker;// 权限检测器
    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.empty, null);
        mPermissionsChecker = new PermissionsChecker(this);
        return mRootView;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRequireCheck) {
            if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                requestPermissions(PERMISSIONS);
                isRequireCheck = true;
            } else {
                showToast("权限已获取");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_PERMISSION_SETTING:
                isRequireCheck = true;
                break;
        }
    }
}
