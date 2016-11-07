package lx.base.apphall.custom_view.search_view;

import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2016/9/29
 * Created by linxiao.
 */

public class SearchViewActivity extends BaseActionBarActivity implements
        SearchView.OnQueryTextListener{
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.lv_list)
    ListView lv;
    private View mRootView;

    private final String[] mData = { "a", "abc", "2b", "aaaaa", "bbbbbb", "cccccc", "ddddddd" };

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.searchview_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mData));
        lv.setTextFilterEnabled(true);//设置lv可以被过虑
        // 设置该SearchView默认是否自动缩小为图标
        searchView.setIconifiedByDefault(false);
        // 为该SearchView组件设置事件监听器
        searchView.setOnQueryTextListener(this);
        // 设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        // 设置该SearchView内默认显示的提示文本
        searchView.setQueryHint("查询");
    }

    @Override
    protected void initParm() {
        super.initParm();
    }

    /**
     * 单击搜索按钮时激发该方法
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        // 实际应用中应该在该方法内执行实际查询
        // 此处仅使用Toast显示用户输入的查询内容
        Toast.makeText(this, "您的选择是:" + query, Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 用户输入字符时激发该方法
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            // 清除ListView的过滤
            lv.clearTextFilter();
        } else {
            // 使用用户输入的内容对ListView的列表项进行过滤
            lv.setFilterText(newText);
            lv.dispatchDisplayHint(View.INVISIBLE);
        }
        return true;
    }
}
