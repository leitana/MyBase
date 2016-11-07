package lx.base.apphall.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import lx.base.apphall.R;

/**
 * Created by Administrator on 2016/7/15.
 */
public class GridViewAdapter extends BaseAdapter{

    private Context context;
    private List<String> list;
    private LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_item);
        Random mRandom = new Random();
        int ranColor = 0xff000000 | mRandom.nextInt(0x00ffffff);
        textView.setBackgroundColor(ranColor);
        if(position < list.size()) {
            textView.setText(list.get(position));
        } else {
            textView.setText("加入");
        }
        return convertView;
    }
}
