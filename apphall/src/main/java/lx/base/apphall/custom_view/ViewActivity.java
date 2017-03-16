package lx.base.apphall.custom_view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import lx.base.apphall.R;

/**
 * 创建时间 2016/9/20
 * Created by linxiao.
 */
public class ViewActivity extends Activity {
    private Boolean isBlack = false;
    private First_view custom_view;
    private TextView theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences1 = getSharedPreferences("user", Context.MODE_PRIVATE);
        isBlack = preferences1.getBoolean("isBlack", false);
        if (isBlack) {
            setTheme(R.style.dayTheme);
        } else {
            setTheme(R.style.nightTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_view);
        custom_view = (First_view) findViewById(R.id.custom_view);
        theme = (TextView) findViewById(R.id.theme);
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isBlack", !isBlack);
                editor.commit();
                recreate();
            }
        });
    }
}
