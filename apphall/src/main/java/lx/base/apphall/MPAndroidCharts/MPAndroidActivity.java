package lx.base.apphall.MPAndroidCharts;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.MPAndroidCharts.BarChart.BarChartActivity;
import lx.base.apphall.MPAndroidCharts.MultipleBar.MultipleBarChartActivity;
import lx.base.apphall.MPAndroidCharts.adapter.MyAdapter;
import lx.base.apphall.MPAndroidCharts.beans.ContentItem;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/6/13.
 */

public class MPAndroidActivity extends BaseActionBarActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.listView1)
    ListView listView1;
    private View mRootView;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.listview_layout, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }


    @Override
    protected void initView() {
        super.initView();
        setMyActionBarTitle("MPAndroidCharts");
        ArrayList<ContentItem> objects = new ArrayList<ContentItem>();
        objects.add(new ContentItem("Bar Chart", "A simple demonstration of the bar chart."));
        objects.add(new ContentItem("Multiple Bars Chart",
                "A bar chart with multiple DataSet objects. One multiple colors per DataSet."));
        MyAdapter adapter = new MyAdapter(this, objects);

        ListView lv = (ListView) findViewById(R.id.listView1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i;
        switch (position) {
            case 0:
                i = new Intent(this, BarChartActivity.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(this, MultipleBarChartActivity.class);
                startActivity(i);
                break;
        }
    }
}
