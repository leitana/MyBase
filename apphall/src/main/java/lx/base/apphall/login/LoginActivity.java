package lx.base.apphall.login;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.MainActivity;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by Administrator on 2016/8/17.
 */
public class LoginActivity extends BaseActionBarActivity {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_userclear)
    ImageView ivUserclear;
    @BindView(R.id.iv_passwordclear)
    ImageView ivPasswordclear;
    @BindView(R.id.cb_remmember)
    CheckBox cbRemmember;
    @BindView(R.id.cb_auto)
    CheckBox cbAuto;
    @BindView(R.id.iv_passvisible)
    ImageView ivPassvisible;
    @BindView(R.id.iv_passinvisible)
    ImageView ivPassinvisible;
    @BindView(R.id.rl_pass)
    RelativeLayout rlPass;
    @BindView(R.id.bt_login)
    Button login;
    private View mRootView;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.login_activity, null);
        Logger.init();
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        hideActionBar();
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏密码
        etPassword.setSelection(etPassword.getText().toString().length());
        clear();
        hidePassword();
        check();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void clear() {
        ivUserclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsername.setText("");
            }
        });
        ivPasswordclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPassword.setText("");
            }
        });
    }

    private void hidePassword() {
        rlPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ivPassvisible.getVisibility() == View.VISIBLE) {
                    ivPassvisible.setVisibility(View.GONE);
                    ivPassinvisible.setVisibility(View.VISIBLE);
                } else {
                    ivPassvisible.setVisibility(View.VISIBLE);
                    ivPassinvisible.setVisibility(View.GONE);
                }
                if (ivPassvisible.getVisibility() == View.VISIBLE) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//显示密码
                    etPassword.setSelection(etPassword.getText().toString().length());
                } else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏密码
                    etPassword.setSelection(etPassword.getText().toString().length());
                }
            }
        });
    }
    private void check() {
        String str = "123qe;/.DA";
        /**
         * 正则表达式：(?=pattern)在任何匹配pattern的地方开始查找
         * (?=.*?[A-Z])：表示必须要有大写字母
         * (?=.*?[0-9])：表示必须要有数字
         * (?=.*?[\x21-\x7e])：必须包含特殊字符
         * [a-zA-Z0-9\x21-\x7e]：表示可以包含字母，数字，特殊字符
         * {10,}：表示要大于或等于10位
         */
        String pattern = "^(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[\\x21-\\x7e])[a-zA-Z0-9\\x21-\\x7e]{10,}$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        etUsername.setText(m.matches() + "");
        System.out.println(m.matches());
        Logger.d(m.matches());
    }

}
