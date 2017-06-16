package lx.base.apphall.MPAndroidCharts.BarChart;

import android.graphics.Matrix;
import android.graphics.Typeface;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/6/12.
 */

public class BarChartActivity extends BaseActionBarActivity {
    @BindView(R.id.bar_chart)
    BarChart mChart;
    private View mRootView;
    //    private List<BarBeans> mDatas;
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
    protected void initParm() {
        super.initParm();
//        mDatas = new ArrayList<>();
//        for (int i = 0; i < 22; i++) {
//            BarBeans barBeans = new BarBeans();
//            barBeans.setValue((float) (Math.random() * 2000));
//            barBeans.setxName("成都" + i);
//            mDatas.add(barBeans);
//        }
    }

    @Override
    protected void initView() {
        super.initView();
        setMyActionBarTitle("柱状图");
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getDescription().setEnabled(true);
        mChart.setMaxVisibleValueCount(60);//最大显示的个数。超过60个将不再显示
        mChart.setPinchZoom(false);
        mChart.setNoDataText("没数据时展示信息");
        mChart.setTouchEnabled(true); // 设置是否可以触摸
        mChart.setScaleEnabled(true);     //禁止缩放
        mChart.setDragEnabled(true);// 是否可以拖拽
        mChart.setHighlightPerDragEnabled(true);// 拖拽超过图标绘制画布时高亮显示
        mChart.setDrawGridBackground(false);
        mChart.getXAxis().setEnabled(true);
        mChart.getAxisRight().setEnabled(false);
//        mChart.getAxisLeft().setEnabled(false);

        Description description = new Description();
        description.setText("表的描述信息");
        mChart.setDescription(description);  //表的描述信息

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(90);//柱的下面描述文字  旋转90度
        xAxis.setDrawLabels(true);//水平线
        xAxis.setDrawGridLines(false);//网格线
        xAxis.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));//字体的相关的设置
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
//        xAxis.setCenterAxisLabels(true);//字体下面的标签 显示在每个直方图的中间(分组直方图中间)
        xAxis.setLabelCount(23, false);//一个界面显示22个Lable。那么这里要设置11个
        xAxis.setTextSize(10f);
        xAxis.setXOffset(0);
        xAxis.setValueFormatter(new MyXFormatter());

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setYOffset(10f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

//        YAxis rightAxis = mChart.getAxisRight();
//        rightAxis.setAxisMinimum(0f);
//        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        rightAxis.setLabelCount(8, false);
//        rightAxis.setYOffset(10f);
//        rightAxis.setSpaceTop(15f);

        //.设置比例图标的显示隐藏
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        CustomMarkerView markerView = new CustomMarkerView(BarChartActivity.this, R.layout.custom_marker_view, areaName);
        markerView.setChartView(mChart); // For bounds control
        mChart.setMarker(markerView); // Set the marker to the chart

        setData();

        //使柱状图可以左右显示
        Matrix mMatrix = new Matrix();
        mMatrix.postScale(1.5f, 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        mChart.getViewPortHandler().refresh(mMatrix, mChart, false);//将图表动画显示之前进行缩放
//        mChart.animateX(1000); // 立即执行的动画,x轴
        mChart.animateY(800);
        mChart.moveViewToX(4);
    }

    private void setData() {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < 22; i++) {
            yVals1.add(new BarEntry(i + 1, (float) (Math.random() * 2000)));
        }
        BarDataSet set1;
        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "地市信息");
            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
            data.setBarWidth(0.9f);
            mChart.setData(data);
        }
    }
}
