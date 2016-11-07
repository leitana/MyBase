package lx_base.mybase.common.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <Pre>
 * 基础Adapter
 * </Pre>
 * 示例如下
 private class DepartmentAdapter extends AdapterBase<TemplateDiseaseEntity> {
 public DepartmentAdapter(List<TemplateDiseaseEntity> mDataList, Context context) {
 super(mDataList, context);
 }

 @Override
 public View getView(int position, View convertView, ViewGroup parent) {
 if (convertView == null) {
 convertView = getLayoutInflater().inflate(R.layout.visit_choose_department_list_item, parent, false);
 }

 CheckBox check = obtainViewFromViewHolder(convertView, R.id.rb_check);
 TextView deparment = obtainViewFromViewHolder(convertView, R.id.tv_department);
 TextView number = obtainViewFromViewHolder(convertView, R.id.tv_number);

 TemplateDiseaseEntity item = getItem(position) != null ? (TemplateDiseaseEntity) getItem(position) : null;

 deparment.setText(item.getName());
 number.setText(item.getCount() + "次使用");

 if (mLastClickPosition == position) {
 check.setChecked(true);
 } else {
 check.setChecked(false);
 }

 return convertView;
 }
 }
 */
public abstract class AdapterBase<T> extends BaseAdapter {
    private  final String TAB = this.getClass().getSimpleName();

    protected Context mContext;
    private List<T> mDataList;
    private LayoutInflater mInflater;

    protected AdapterBase(List<T> dataList, Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is not allow null!");
        }
        this.mDataList = dataList;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);

        if (dataList == null) {
            this.mDataList = new ArrayList();
        }
    }

    public LayoutInflater getLayoutInflater() {
        return mInflater;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * 快速获取view
     * @param container         容器
     * @param id                组件id
     * @param <T>
     * @return
     */
    public <T extends View> T obtainViewFromViewHolder(View container, int id) {
        return ViewHolder.get(container, id);
    }

    @Override
    public int getCount() {
        if (mDataList != null) {
            return mDataList.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        if (mDataList != null && mDataList.size() > 0) {
            return mDataList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDataList(List<T> dataList) {
        this.mDataList = dataList;
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public void addAll(List<T> list) {
        mDataList.addAll(list);
    }
}
