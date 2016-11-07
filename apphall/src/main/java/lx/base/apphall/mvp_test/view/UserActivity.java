package lx.base.apphall.mvp_test.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx.base.apphall.mvp_test.presenter.UserPresenter;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/9/8
 * Created by linxiao.
 */
public class UserActivity extends BaseActionBarActivity implements View.OnClickListener, IUserView {
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.et_fn)
    EditText etFn;
    @BindView(R.id.et_ln)
    EditText etLn;
    @BindView(R.id.mSaveBt)
    Button mSaveBt;
    @BindView(R.id.mLoadBt)
    Button mLoadBt;

    private View mRootView;
    private UserPresenter mUserPresenter;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.mvp_activity, null);
        ButterKnife.bind(this, mRootView);
        mUserPresenter = new UserPresenter(this);
        mSaveBt.setOnClickListener(this);
        mLoadBt.setOnClickListener(this);
        return mRootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mSaveBt:
                mUserPresenter.saveUser(getID(), getFirstName(), getLastName());
                break;
            case R.id.mLoadBt:
                mUserPresenter.loadUser(getID());
                break;
            default:
                break;
        }
    }


    @Override
    public int getID() {
        return Integer.parseInt(etId.getText().toString());
    }

    @Override
    public String getFirstName() {
        return etFn.getText().toString();
    }

    @Override
    public String getLastName() {
        return etLn.getText().toString();
    }

    @Override
    public void setFirstName(String firstName) {
        etFn.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        etLn.setText(lastName);
    }
}
