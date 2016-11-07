package lx_base.mybase.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/14.
 */
public class BaseFragment extends Fragment{
    private Toast toast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentView();
        initFragmentParam();
    }
    /**
     * 对象初始化
     */
    protected void initFragmentParam() {

    }

    /**
     * 页面初始化
     */
    protected void initFragmentView() {

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
            toast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
        }
        return toast;
    }
}
