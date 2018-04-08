package lx.base.apphall.MPAndroidCharts.BarChart;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import lx.base.apphall.R;

/**
 * Created by 11300 on 2017/6/13.
 */

public class CustomMarkerView extends MarkerView {
    private TextView tvContent;
    protected String[] areaName;
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public CustomMarkerView(Context context, int layoutResource, String[] areaName) {
        super(context, layoutResource);
        tvContent = (TextView) findViewById(R.id.tvContent);
        this.areaName = areaName;
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(areaName[(int)e.getX()] + "--" + e.getX()+ "--" + e.getY() + "--" + e.getData());
        super.refreshContent(e, highlight);
    }

    //设置mark坐标
    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
