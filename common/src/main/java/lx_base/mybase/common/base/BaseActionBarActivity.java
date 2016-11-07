package lx_base.mybase.common.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import lx_base.mybase.R;
import me.drakeet.materialdialog.MaterialDialog;


/**
 * 带actionbar的基础activity
 * Created by Administrator on 2016/7/14.
 */
public abstract class BaseActionBarActivity extends BaseUIActivity {
    private static final int PERMISSION_REQUEST_CODE = 0; // 系统权限管理页面的参数
    public static final int PERMISSIONS_GRANTED = 0; // 权限授权
    public static final int PERMISSIONS_DENIED = 1; // 权限拒绝
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    public static final int REQUEST_PERMISSION_SETTING = 3;

    public boolean isRequireCheck = true;// 是否需要系统权限检测, 防止和系统提示框重叠

    private LinearLayout leftLayout;
    private ImageView imageViewLeft, imageViewRight2, imageViewRight1;
    private TextView textViewTitle, textViewLeft, textViewRight1, textViewRight2;
    private Dialog mLoadProgress;
    private MaterialDialog mMaterialDialog;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected View setMyActionBarView() {
        View view = getLayoutInflater().inflate(R.layout.actionbar_common_layout, null);
        leftLayout = (LinearLayout) view.findViewById(R.id.ll_left);
        textViewLeft = (TextView) view.findViewById(R.id.tv_left1);
        imageViewLeft = (ImageView) view.findViewById(R.id.iv_left);
        imageViewRight2 = (ImageView) view.findViewById(R.id.iv_right2);
        imageViewRight1 = (ImageView) view.findViewById(R.id.iv_right1);
        textViewTitle = (TextView) view.findViewById(R.id.tv_title);
        textViewRight1 = (TextView) view.findViewById(R.id.tv_right1);
        textViewRight2 = (TextView) view.findViewById(R.id.tv_right2);

        leftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMyLeftView();
            }
        });
        imageViewRight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMyRightImageView2();
            }
        });
        imageViewRight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMyRightImageView1();
            }
        });
        textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMyTitleView();
            }
        });
        textViewRight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMyRightTextView1();
            }
        });
        textViewRight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMyRightTextView2();
            }
        });
        return view;
    }

    /**
     * 设置title
     *
     * @param title
     */
    public void setMyActionBarTitle(String title) {
        if (textViewTitle != null && !TextUtils.isEmpty(title)) {
            textViewTitle.setText(title);
        }
    }

    public void hideMyActionBarTitle() {
        if (textViewTitle != null) {
            textViewTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置左边按钮效果
     *
     * @param res
     */
    public void setMyLeftImageView(int res) {
        if (imageViewLeft != null && res >= 0) {
            imageViewLeft.setImageResource(res);
            leftLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置左边文字
     *
     * @param text
     */
    public void setMyLeftTextView1(String text) {
        if (textViewLeft != null && !TextUtils.isEmpty(text)) {
            textViewLeft.setVisibility(View.VISIBLE);
            textViewLeft.setText(text);
        }
    }

    /**
     * 设置右边第一个按钮的效果
     */
    public void setMyRightImageView1(int res) {
        if (imageViewRight1 != null && res >= 0) {
            imageViewRight1.setImageResource(res);
            imageViewRight1.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置右边第二个按钮的效果
     */
    public void setMyRightImageView2(int res) {
        if (imageViewRight2 != null && res >= 0) {
            imageViewRight2.setImageResource(res);
            imageViewRight2.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 设置右边第二个文字
     *
     * @param text
     */
    public void setMyRightTextView2(String text) {
        if (textViewRight2 != null && !TextUtils.isEmpty(text)) {
            textViewRight2.setVisibility(View.VISIBLE);
            textViewRight2.setText(text);
        }
    }

    /**
     * 设置右边第一个文字
     *
     * @param text
     */
    public void setMyRightTextView1(String text) {
        if (textViewRight1 != null && !TextUtils.isEmpty(text)) {
            textViewRight1.setVisibility(View.VISIBLE);
            textViewRight1.setText(text);
        }
    }

    /**
     * 显示左边按钮
     */
    public void showMyLeftView() {
        if (leftLayout != null) {
            leftLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏左边按钮
     */
    public void hideMyLeftView() {
        if (imageViewLeft != null) {
            imageViewLeft.setVisibility(View.GONE);
        }
    }

    public void showMyLeftTextView() {
        if (textViewLeft != null) {
            textViewLeft.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示右边第一个按钮
     */
    public void showMyRightImageView1() {
        if (imageViewRight1 != null) {
            imageViewRight1.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏右边第一个按钮
     */
    public void hideMyRightImageView1() {
        if (imageViewRight1 != null) {
            imageViewRight1.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 显示右边第二个按钮
     */
    public void showMyRightImageView2() {
        if (imageViewRight2 != null) {
            imageViewRight2.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏右边第二个按钮
     */
    public void hideMyRightImageView2() {
        if (imageViewRight2 != null) {
            imageViewRight2.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 隐藏右边第二個文字
     */
    public void hideMyRightTextView2() {
        if (textViewRight2 != null) {
            textViewRight2.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 显示右边第二個文字
     */
    public void showMyRightTextView2() {
        if (textViewRight2 != null) {
            textViewRight2.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏右边第一個文字
     */
    public void hideMyRightTextView1() {
        if (textViewRight1 != null) {
            textViewRight1.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 显示右边第一個文字
     */
    public void showMyRightTextView1() {
        if (textViewRight1 != null) {
            textViewRight1.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 点击左边按钮事件
     */
    public void clickMyLeftView() {
        finish();
    }

    /**
     * 点击右边第二个按钮
     */
    public void clickMyRightImageView2() {

    }

    /**
     * 点击右边第一个按钮
     */
    public void clickMyRightImageView1() {

    }

    /**
     * 点击右边第二个文字事件
     */
    public void clickMyRightTextView2() {

    }

    /**
     * 点击右边第一个文字事件
     */
    public void clickMyRightTextView1() {

    }

    /**
     * 点击title view事件
     */
    public void clickMyTitleView() {

    }

    /**
     * 隐藏右边所有视图
     */
    public void hideMyAllRightView() {
        hideMyRightImageView1();
        hideMyRightImageView2();
        hideMyRightTextView1();
        hideMyRightTextView2();
    }

    /**
     * 显示进度条
     */
    public void showLoading() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.load_layout, null);

        mLoadProgress = new Dialog(this, R.style.loading_dialog);
        mLoadProgress.setCancelable(true);
        mLoadProgress.setContentView(v, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));
        mLoadProgress.show();
    }

    /**
     * 隐藏进度条
     */
    public void hideLoading() {
        if (mLoadProgress != null && mLoadProgress.isShowing()) {
            mLoadProgress.dismiss();
        }
    }

    /**
     * dialog
     *
     * @param title
     * @param message
     * @param negative
     * @param postive
     */
    public void showMaterialDialog(String title, String message, String negative, String postive) {
        mMaterialDialog = new MaterialDialog(this);
        mMaterialDialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton(postive, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                })
                .setNegativeButton(negative, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });
        mMaterialDialog.show();
    }

    /**
     * dialog
     *
     * @param title
     * @param message
     * @param negative
     * @param postive
     * @param negativeOnClickListener
     * @param positiveOnClickListener
     */
    public void showMaterialDialog(String title, String message, String negative, String postive,
                                   View.OnClickListener negativeOnClickListener, View.OnClickListener positiveOnClickListener) {
        mMaterialDialog = new MaterialDialog(this);
        mMaterialDialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton(postive, positiveOnClickListener)
                .setNegativeButton(negative, negativeOnClickListener);
        mMaterialDialog.show();
    }

    /**
     * 隐藏dialog
     */
    public void dismissMaterialDialog() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
        }
    }

    public View getMyRightTextView2() {
        return textViewRight2;
    }

    public View getMyRightImageView2() {
        return imageViewRight2;
    }

    public View getMyLeftTextView() {
        return textViewLeft;
    }

    public View getLeftLayout() {
        return leftLayout;
    }

    /**
     * 显示popupwindow
     *
     * @param view
     * @param text
     */
    public void showPopupWindow(View view, String text) {
        View contentView = getLayoutInflater().inflate(R.layout.popwindow_layout, null);
        TextView textView = (TextView) contentView.findViewById(R.id.tv_pop);
        textView.setText(text);
        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.ico_help_bg));
        popupWindow.showAsDropDown(view);
    }

    public void showProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在加载...");
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dissmissProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }



    // 显示缺失权限提示
    public void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.help);
        builder.setMessage(R.string.string_help_text);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                setResult(PERMISSIONS_DENIED);
                finish();
            }
        });

        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.setCancelable(false);

        builder.show();
    }

    public void requestPermissions(String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }
    // 含有全部的权限
    private boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        boolean flag = true;
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                flag = false;
            }
        }
        if (flag) {
            return true;
        } else {
            return false;
        }
    }
    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionsGranted(grantResults)) {
            isRequireCheck = true;
        } else {
            isRequireCheck = false;
            showMissingPermissionDialog();
        }
    }

    public void showSnackbar(View view, String message) {
        Snackbar mSnackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        mSnackBar.show();
    }
}
