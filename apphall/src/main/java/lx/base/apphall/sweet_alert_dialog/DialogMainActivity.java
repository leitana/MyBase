package lx.base.apphall.sweet_alert_dialog;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/11/23
 * Created by linxiao.
 */

public class DialogMainActivity extends BaseActionBarActivity {
    private View mRootView;
    private SweetAlertDialog sweetAlertDialog;
    private Context context = DialogMainActivity.this;

    @OnClick(R.id.progress)
    public void onClick() {
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading");
        sweetAlertDialog.setCancelable(true);
        sweetAlertDialog.show();
    }

    @OnClick(R.id.title_content)
    public void onClickTitleContent() {
        sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog
                .setTitleText("Title with content")
                .setContentText("this is content")
                .show();
    }

    @OnClick(R.id.error)
    public void onClickError() {
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog
                .setTitleText("Error")
                .setContentText("There was a problem")
                .setCancelText("cancle")
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                })
                .show();
    }

    @OnClick(R.id.warning)
    public void onClickWarning() {
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog
                .setTitleText("Warning")
                .setContentText("There was a warning")
                .setCancelText("cancle")
                .show();
    }

    @OnClick(R.id.success)
    public void onClickSuccess() {
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog
                .setTitleText("Success")
                .setContentText("Done")
                .setCancelText("cancle")
                .show();
    }

    @OnClick(R.id.image)
    public void onClickImage() {
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        sweetAlertDialog
                .setTitleText("Image")
                .setContentText("Here is a custom image.")
                .setCustomImage(R.mipmap.user)
                .show();
    }

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.dialog_main_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initParm() {
        super.initParm();
    }

    @Override
    protected void initView() {
        super.initView();
    }

}
