package lx.base.apphall.timepicker;

import android.view.View;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/10/25
 * Created by linxiao.
 */

public class TimePickerActivity extends BaseActionBarActivity{
    @OnClick(R.id.pick)
    public void onClickpick(){
        pvTime.show();
    }
    private View mRootView;
    private TimePickerView pvTime;
    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.timepicker_activity, null);
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
        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.ALL);
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);

        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                showToast(getTime(date) + "");
            }
        });
    }
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
