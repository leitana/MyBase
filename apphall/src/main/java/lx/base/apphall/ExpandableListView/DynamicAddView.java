package lx.base.apphall.ExpandableListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx.base.apphall.ExpandableListView.beans.AddViewBean;
import lx.base.apphall.ExpandableListView.beans.TestResult;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2017/2/14
 * Created by linxiao.
 */

public class DynamicAddView extends BaseActionBarActivity {
    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.expandablelistview)
    ExpandableListView expandableListView;

    private Context mContext = DynamicAddView.this;
    private MyExpandListViewAdapter mAdapter;
    private View mRootView;

    private List<AddViewBean> mData;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.dynamic_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initParm() {
        super.initParm();
    }

    @Override
    protected void initView() {
        super.initView();
        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AddViewBean addViewBean = new AddViewBean();
            List<TestResult> testResultsList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                TestResult testResult = new TestResult();
                testResult.setFtpUp((j + 1) * 30);
                testResultsList.add(testResult);
            }
            addViewBean.setTestResults(testResultsList);
            mData.add(addViewBean);
        }
        mAdapter = new MyExpandListViewAdapter();
        expandableListView.setAdapter(mAdapter);
        for (int i = 0; i < mAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
    }


    private class MyExpandListViewAdapter extends BaseExpandableListAdapter {

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
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.include_work_write, null);
            }
            Button bt_test = (Button) convertView.findViewById(R.id.bt_test);
            bt_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TestResult testResult = new TestResult();
                    testResult.setFtpUp(70);
                    if (mData.get(groupPosition).getTestResults() != null) {
                        mData.get(groupPosition).getTestResults().add(testResult);
                    }
                    notifyDataSetChanged();
                }
            });
            return convertView;
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
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.include_result, null);
            }
            ProgressBar progress = (ProgressBar) convertView.findViewById(R.id.progress);
            View delete = convertView.findViewById(R.id.delete);
            progress.setProgress(mData.get(groupPosition).getTestResults().get(childPosition).getFtpUp());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.get(groupPosition).getTestResults().remove(childPosition);
                    notifyDataSetChanged();
                }
            });
            return convertView;
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
}
