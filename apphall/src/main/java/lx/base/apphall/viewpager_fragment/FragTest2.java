package lx.base.apphall.viewpager_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lx.base.apphall.R;

/**
 * Created by Administrator on 2016/8/9.
 */
public class FragTest2 extends Fragment{
    private View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment2, null);
        return mRootView;
    }

    public static FragTest2 newInstance() {
        FragTest2 fragment = new FragTest2();
        return fragment;
    }
}
