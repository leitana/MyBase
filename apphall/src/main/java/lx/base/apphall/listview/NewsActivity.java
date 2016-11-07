package lx.base.apphall.listview;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx.base.apphall.beans.JockContent;
import lx.base.apphall.beans.JockerResponse;
import lx_base.mybase.common.base.AdapterBase;
import lx_base.mybase.common.base.BaseActionBarActivity;
import lx_base.mybase.common.util.OkHttpUtils;
import lx_base.mybase.common.widgets.pull_refresh.PullToRefreshBase;
import lx_base.mybase.common.widgets.pull_refresh.PullToRefreshListView;

/**
 * Created by Administrator on 2016/8/22.
 */
public class NewsActivity extends BaseActionBarActivity {

    private String httpUrl = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text";
    private String httpArg = "page=";
    private String Url;
    private int mRequestTotal = 0;
    private int mCurrentPage = 1;

    @BindView(R.id.lv_news)
    PullToRefreshListView mListView;

    private View mRootView;
    private List<JockContent> mData;
    private MyAdapter mAdapter;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.news_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        mData = new ArrayList<>();
        initData(1);
        mListView.getRefreshableView().setDivider(null);
        mListView.setPullLoadEnabled(true);
        mListView.setPullRefreshEnabled(true);
        mListView.setHasMoreData(true);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mData != null) {
                    if (mData.size() > 0) {
                        mData.clear();
                    }
                }
                initData(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mRequestTotal / 20 > 0) {
                    mRequestTotal -= 20;
                    initData(mCurrentPage + 1);
                } else {
                    mListView.onPullUpRefreshComplete();
                }
            }
        });
        mAdapter = new MyAdapter(mData, NewsActivity.this);
        mListView.getRefreshableView().setAdapter(mAdapter);
    }

    private void initData(int page) {
        Url = httpUrl + "?" + httpArg + page;
        final Gson gson = new Gson();

        showProgressDialog();
        OkHttpUtils.ResultCallback<String> loadJockCallBack = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                JockerResponse jockerResponse = gson.fromJson(response, JockerResponse.class);
                mData.addAll(jockerResponse.getShowapi_res_body().getContentlist());
                mRequestTotal = Integer.parseInt(jockerResponse.getShowapi_res_body().getAllNum());
                mAdapter.notifyDataSetChanged();
                mListView.onPullDownRefreshComplete();
                mListView.onPullUpRefreshComplete();
                dissmissProgressDialog();
            }

            @Override
            public void onFailure(Exception e) {
                dissmissProgressDialog();
                mListView.onPullDownRefreshComplete();
                mListView.onPullUpRefreshComplete();

            }

        };
        OkHttpUtils.getWithHead(Url, loadJockCallBack, "apikey","529cad5d92f049c0165be5fdfbf210b8");
    }

    class MyAdapter extends AdapterBase<JockContent> {


        protected MyAdapter(List<JockContent> dataList, Context context) {
            super(dataList, context);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public JockContent getItem(int position) {
            return mData.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            JockContent content = mData.get(position);
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.news_item, parent, false);
            }
            TextView title = obtainViewFromViewHolder(convertView, R.id.title);
            TextView context = obtainViewFromViewHolder(convertView, R.id.context);
            title.setText(content.getTitle());
            context.setText(Html.fromHtml(content.getText()));
            return convertView;
        }
    }
}
