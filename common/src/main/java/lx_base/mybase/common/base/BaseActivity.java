package lx_base.mybase.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * 基础父类
 * Created by Administrator on 2016/7/5.
 */
public class BaseActivity extends FragmentActivity{
    // 手机网络是否ok
    private boolean isConnected;

    // 是否正在使用wifi
    private boolean isWifi;

    // toast
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        initParm();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 对象初始化
     */
    protected void initParm(){
    }

    /**
     * 页面初始化
     */
    protected void initView(){

    }
    /**
     * 显示toast
     *
     * @param s
     * @param d
     */
    protected void showToast(CharSequence s, int d) {
        getToast().setText(s);
        getToast().setDuration(d);
        getToast().show();
    }
    /**
     * 显示toast
     *
     * @param s
     */
    protected void showToast(CharSequence s) {
        showToast(s, Toast.LENGTH_SHORT);
    }

    /**
     * toast get func
     *
     * @return
     */
    public Toast getToast() {
        if (toast == null) {
            toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        return toast;
    }
}
