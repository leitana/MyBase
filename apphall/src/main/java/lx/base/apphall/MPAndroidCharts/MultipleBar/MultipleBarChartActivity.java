package lx.base.apphall.MPAndroidCharts.MultipleBar;

import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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
    @BindView(R.id.bar_chart)
    BarChart barChart;
    private View mRootView;

    public ArrayList<BarEntry> entries = new ArrayList<BarEntry>(); //(x,y1)
    public ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();//(x,y2)
    public ArrayList<BarEntry> entries3= new ArrayList<BarEntry>();//(x,y3)
    public BarDataSet dataset ,dataset2,dataset3;

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
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));
        entries.add(new BarEntry(4f, 6));
        entries.add(new BarEntry(8f, 7));
        entries.add(new BarEntry(6f, 8));
        entries.add(new BarEntry(12f, 9));
        entries.add(new BarEntry(18f, 10));
        entries.add(new BarEntry(9f, 11));

        entries2.add(new BarEntry(5f, 0));
        entries2.add(new BarEntry(6f, 1));
        entries2.add(new BarEntry(7f, 2));
        entries2.add(new BarEntry(5f, 3));
        entries2.add(new BarEntry(13f, 4));
        entries2.add(new BarEntry(12f, 5));
        entries2.add(new BarEntry(5f, 6));
        entries2.add(new BarEntry(6f, 7));
        entries2.add(new BarEntry(7f, 8));
        entries2.add(new BarEntry(5f, 9));
        entries2.add(new BarEntry(13f, 10));
        entries2.add(new BarEntry(12f, 11));

        entries3.add(new BarEntry(8f, 0));
        entries3.add(new BarEntry(4f, 1));
        entries3.add(new BarEntry(15f, 2));
        entries3.add(new BarEntry(12f, 3));
        entries3.add(new BarEntry(12f, 4));
        entries3.add(new BarEntry(1f, 5));
        entries3.add(new BarEntry(8f, 6));
        entries3.add(new BarEntry(4f, 7));
        entries3.add(new BarEntry(15f, 8));
        entries3.add(new BarEntry(12f, 9));
        entries3.add(new BarEntry(12f, 10));
        entries3.add(new BarEntry(1f, 11));
    }

    public void show() {
        dataset = new BarDataSet(entries, "甲");
        dataset.setColor(Color.rgb(255, 48, 48));
        dataset2 = new BarDataSet(entries2, "乙");
        dataset2.setColor(Color.rgb(0, 191, 255));
        dataset3 = new BarDataSet(entries3, "丙");
        dataset3.setColor(Color.rgb(255, 215, 0));

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>(); //坐标线的集合。
        dataSets.add(dataset);
        dataSets.add(dataset2);
        dataSets.add(dataset3);

        ArrayList<String> xVals = new ArrayList<String>(); //x轴坐标
        for(int i = 1;i<13;i++){
            xVals.add("第"+i+"次");
        }


//        BarData data3 = new BarData(dataSets);
//        barChart.setData(data3);
        barChart.animateY(2000);//动画效果 y轴方向，2秒




    }
}
