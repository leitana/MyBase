package lx.base.apphall.MPAndroidCharts.MultipleBar;

import android.graphics.Color;
import android.graphics.Matrix;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.MPAndroidCharts.BarChart.CustomMarkerView;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/6/15.
 */

public class MultipleBarChartActivity extends BaseActionBarActivity {
    public BarDataSet set1, set2, set3, set4;
    @BindView(R.id.bar_chart)
    BarChart mChart;
    ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yVals4 = new ArrayList<BarEntry>();
    private View mRootView;
    private String[] areaName = new String[]{
            "成都", "绵阳", "乐山", "峨眉", "泸州", "南充", "九寨沟", "攀枝花", "甘孜",
            "成都", "绵阳", "乐山", "峨眉", "泸州", "南充", "九寨沟", "攀枝花", "甘孜",
            "成都", "绵阳", "乐山", "峨眉", "buchong"
    };

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.activity_mpcharts_bar, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();

        mChart.getDescription().setEnabled(false);
        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawGridBackground(false);

        CustomMarkerView markerView = new CustomMarkerView(MultipleBarChartActivity.this, R.layout.custom_marker_view, areaName);
        markerView.setChartView(mChart); // For bounds control
        mChart.setMarker(markerView); // Set the marker to the chart

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setXOffset(0);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int i = (int) value;
                return i < 0 ? "" : areaName[i];
            }
        });

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        mChart.getAxisRight().setEnabled(false);

        initEntriesData();

        show();

        Matrix mMatrix=new Matrix();
        mMatrix.postScale(1.5f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        mChart.getViewPortHandler().refresh(mMatrix, mChart, false);//将图表动画显示之前进行缩放
//        mChart.animateX(1000); // 立即执行的动画,x轴
        mChart.animateY(800);
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


//        BarData data3 = new BarData(xVals, dataSets);
        mChart.setData(data);

        // specify the width each bar should have
        mChart.getBarData().setBarWidth(barWidth);
        // restrict the x-axis range
        mChart.getXAxis().setAxisMinimum(0);
        mChart.getXAxis().setAxisMaximum(6);
        mChart.setData(data);//装载数据
        mChart.groupBars(0, groupSpace, barSpace);
        mChart.invalidate();//刷新
    }
}
