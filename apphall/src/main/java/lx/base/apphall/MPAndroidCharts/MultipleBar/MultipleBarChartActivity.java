package lx.base.apphall.MPAndroidCharts.MultipleBar;

import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/6/15.
 */

public class MultipleBarChartActivity extends BaseActionBarActivity {
    public BarDataSet set1, set2, set3, set4;
    @BindView(R.id.bar_chart)
    BarChart barChart;
    ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yVals4 = new ArrayList<BarEntry>();
    private View mRootView;
    private XAxis xAxis; //X坐标轴
    private YAxis yAxis; //Y

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.activity_mpcharts_bar, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴在下边
        yAxis = barChart.getAxisLeft();
        xAxis.setDrawGridLines(false);//不要竖线网格
        yAxis.setStartAtZero(false);//不一定从0坐标开始
        barChart.getAxisRight().setEnabled(false); // 隐藏右边 的坐标轴
        xAxis.setAvoidFirstLastClipping(false);

        barChart.getAxisRight().setEnabled(false);
        barChart.zoom(3.0f,1.0f,0f,0f);


        initEntriesData();  //添加Y轴数据

        show();
    }

    public void initEntriesData() {
        for (int i = 0; i < 6; i++) {
            yVals1.add(new BarEntry(i, (float) (Math.random() * 20)));
            yVals2.add(new BarEntry(i, (float) (Math.random() * 20)));
            yVals3.add(new BarEntry(i, (float) (Math.random() * 20)));
            yVals4.add(new BarEntry(i, (float) (Math.random() * 20)));
        }
    }

    public void show() {
        float groupSpace = 0.08f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet
        // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"

        set1 = new BarDataSet(yVals1, "Company A");
        set1.setColor(Color.rgb(104, 241, 175));
        set2 = new BarDataSet(yVals2, "Company B");
        set2.setColor(Color.rgb(164, 228, 251));
        set3 = new BarDataSet(yVals3, "Company C");
        set3.setColor(Color.rgb(242, 247, 158));
        set4 = new BarDataSet(yVals4, "Company D");
        set4.setColor(Color.rgb(255, 102, 0));

//        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>(); //坐标线的集合。
//        dataSets.add(dataset);
//        dataSets.add(dataset2);
//        dataSets.add(dataset3);

        BarData data = new BarData(set1, set2, set3, set4);

        ArrayList<String> xVals = new ArrayList<String>(); //x轴坐标
        for(int i = 1;i<13;i++){
            xVals.add("第"+i+"次");
        }


//        BarData data3 = new BarData(xVals, dataSets);
        barChart.setData(data);

        data.setBarWidth(barWidth);
        barChart.setData(data);//装载数据
        barChart.groupBars(0f, groupSpace, barSpace);
        barChart.invalidate();//刷新
    }
}
