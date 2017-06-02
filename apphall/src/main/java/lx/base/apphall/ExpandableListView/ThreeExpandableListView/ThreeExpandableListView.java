package lx.base.apphall.ExpandableListView.ThreeExpandableListView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.ExpandableListView.beans.Classes;
import lx.base.apphall.ExpandableListView.beans.College;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by 11300 on 2017/6/2.
 */

public class ThreeExpandableListView extends BaseActionBarActivity {
    @BindView(R.id.elistone)
    ExpandableListView elistone;

    private Context mContext = ThreeExpandableListView.this;
    private View mRootView;
    private List<College> colleges;
    private EAdapterOne adapterOne;
    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.activity_three_expandable, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        adapterOne = new EAdapterOne();
        elistone.setAdapter(adapterOne);
    }

    @Override
    protected void initParm() {
        super.initParm();
        College college = new College();

        college.name = "科技大学";

        List<Classes> classesList = new ArrayList<>();

        for(int i = 1 ;i<3;i++) {
            Classes classes = new Classes();

            classes.name = "计算机"+i+"班";

            List<String> list = new ArrayList<>();

            list.add("mm");
            list.add("dd");
            classes.students = list;

            classesList.add(classes);
        }

        college.classList = classesList;


        colleges = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            colleges.add(college);
        }
    }

    public class EAdapterOne extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return colleges.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return colleges.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return colleges.get(groupPosition).classList.get(childPosition);
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
            GroupViewHolder holder = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_group_layout, null);
                holder = new GroupViewHolder();
                holder.school = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (GroupViewHolder)convertView.getTag();
            }
            holder.school.setText(colleges.get(groupPosition).name);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            // 返回子ExpandableListView 的对象  此时传入是该父条目，即大学的对象（有歧义。。）
            return getChildExpandableListView(colleges.get(groupPosition));
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    public ExpandableListView getChildExpandableListView(College college) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        CustomExpandableListView view = new CustomExpandableListView(mContext);
        ClassesExpandableListViewAdapter adapter = new ClassesExpandableListViewAdapter(college.classList, this);
        view.setAdapter(adapter);
        view.setPadding(20, 0, 0, 0);
        return view;
    }
    class GroupViewHolder {
        TextView school;
    }
}
