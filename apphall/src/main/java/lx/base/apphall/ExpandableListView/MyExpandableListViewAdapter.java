package lx.base.apphall.ExpandableListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import lx.base.apphall.R;
import lx.base.apphall.ExpandableListView.beans.AddViewBean;

/**
 * 创建时间 2017/2/19
 * Created by linxiao.
 */

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<AddViewBean> mData;

    public MyExpandableListViewAdapter(Context context, List<AddViewBean> dataList) {
        mContext = context;
        mData = dataList;
    }

    /**
     * 获得父项的数量
     *
     * @return
     */
    @Override
    public int getGroupCount() {
        return mData.size();
    }

    /**
     * 获得某个父项的子项的数量
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(groupPosition).getTestResults().size();
    }

    /**
     * 获得某个父项
     *
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    /**
     * 获得某个父类的某个子项
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mData.get(groupPosition).getTestResults().get(childPosition);
    }

    /**
     * 获得某个父项的id
     *
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获得某个父项的某个子项的id
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
     *
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 获得父项显示的view
     *
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.include_work_write, null);
        }

        return null;
    }

    /**
     * 获得子项显示的view
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    /**
     * 子项是否可选中，如果需要设置子项的点击事件，需要返回true
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
