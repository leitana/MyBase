package lx.base.apphall.MPAndroidCharts.BarChart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by 11300 on 2017/6/13.
 */

public class MyXFormatter implements IAxisValueFormatter{

    protected String[] areaName = new String[]{
        "成都", "绵阳", "乐山", "峨眉", "泸州", "南充", "九寨沟", "攀枝花", "甘孜",
            "成都", "绵阳", "乐山", "峨眉", "泸州", "南充", "九寨沟", "攀枝花", "甘孜",
            "成都", "绵阳", "乐山", "峨眉", "buchong"
    };


    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int i = (int) value;
        return i == 0 ? "" : areaName[i - 1];
    }
}
