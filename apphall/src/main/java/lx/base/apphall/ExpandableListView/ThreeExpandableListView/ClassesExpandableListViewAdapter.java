package lx.base.apphall.ExpandableListView.ThreeExpandableListView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import lx.base.apphall.ExpandableListView.beans.Classes;
import lx.base.apphall.R;

/**
 * Created by 11300 on 2017/6/2.
 */

public class ClassesExpandableListViewAdapter extends BaseExpandableListAdapter {
    // 班级的集合
    private List<Classes> classes;

    // 创建布局使用
    private Activity activity;


    public ClassesExpandableListViewAdapter(List<Classes> classes, Activity activity) {
        this.classes = classes;
        this.activity = activity;
    }

    @Override
    public int getGroupCount() {
        return classes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return classes.get(groupPosition).students.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return classes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return classes.get(groupPosition).students.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        SecondViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.item_group_layout, null);
            childViewHolder = new SecondViewHolder();
            childViewHolder.className = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (SecondViewHolder) convertView.getTag();
        }
        childViewHolder.className.setText(classes.get(groupPosition).name);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ThirdViewHolder thirdViewHolder = null;
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.item_group_layout, null);
            thirdViewHolder = new ThirdViewHolder();
            thirdViewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(thirdViewHolder);
        } else {
            thirdViewHolder = (ThirdViewHolder) convertView.getTag();
        }
        thirdViewHolder.name.setText(classes.get(groupPosition).students.get(childPosition));
        return convertView;
    }

    // 根据方法名，此处应该表示二级条目是否可以被点击
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class SecondViewHolder{
        TextView className;
    }
    class ThirdViewHolder{
        TextView name;
    }
}
