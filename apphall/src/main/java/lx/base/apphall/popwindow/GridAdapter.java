package lx.base.apphall.popwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import lx.base.apphall.R;
import lx_base.mybase.common.util.ImageLoaderUtils;

/**
 * 创建时间 2016/9/22
 * Created by linxiao.
 */
public class GridAdapter extends BaseAdapter{

    private Context context;
    private List<String> list;
    private LayoutInflater layoutInflater;

    public GridAdapter(Context context, List<String> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.grid_image_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_item_image);
            viewHolder.delete = (ImageView) convertView.findViewById(R.id.iv_item_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position < list.size()) {
            ImageLoaderUtils.display(context, viewHolder.imageView, list.get(position));
            viewHolder.delete.setVisibility(View.VISIBLE);
        } else {
            Glide.with(context).load(R.mipmap.ic_add_pick).placeholder(lx_base.mybase.R.mipmap.ic_image_loading)
                    .error(lx_base.mybase.R.mipmap.ic_image_loadfail).crossFade().into(viewHolder.imageView);
            viewHolder.delete.setVisibility(View.GONE);
        }
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetInvalidated();
            }
        });

        return convertView;
    }
    private static class ViewHolder{
        ImageView imageView;
        ImageView delete;
    }
}
