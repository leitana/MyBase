package lx.base.apphall.listview.sample_news.view;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx.base.apphall.beans.JockContent;
import lx.base.apphall.listview.sample_news.presenter.NewsPresenter;
import lx.base.apphall.listview.sample_news.presenter.NewsPresenterImpl;
import lx_base.mybase.common.base.AdapterBase;
import lx_base.mybase.common.base.BaseActionBarActivity;
import lx_base.mybase.common.widgets.pull_refresh.PullToRefreshBase;
import lx_base.mybase.common.widgets.pull_refresh.PullToRefreshListView;

/**
 * 创建时间 2016/9/12
 * Created by linxiao.
 */
public class NewsView extends BaseActionBarActivity implements INewsView {
    @BindView(R.id.lv_news)
    PullToRefreshListView mListView;

    private View mRootView;
    private List<JockContent> mData;
    private NewsPresenter newsPresenter;
    private MyAdapter mAdpater;
    private int mRequestTotal = 60;
    private int mCurrentPage = 1;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.news_activity, null);
        ButterKnife.bind(this, mRootView);
        newsPresenter = new NewsPresenterImpl(this);
        mListView.getRefreshableView().setDivider(null);
        mListView.setPullLoadEnabled(true);
        mListView.setPullRefreshEnabled(true);
        mListView.setHasMoreData(true);
        newsPresenter.loadNews(1);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mData != null) {
                    if (mData.size() > 0) {
                        mData.clear();
                    }
                }
                newsPresenter.loadNews(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mRequestTotal / 20 > 0) {
                    mRequestTotal -= 20;
                    mCurrentPage = mCurrentPage + 1;
                    newsPresenter.loadNews(mCurrentPage);
                } else {
                    mListView.onPullUpRefreshComplete();
                }
            }
        });
        return mRootView;
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        dissmissProgressDialog();
        mListView.onPullDownRefreshComplete();
        mListView.onPullUpRefreshComplete();
    }

    @Override
    public void addNews(List<JockContent> newsList) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(newsList);

        if (mCurrentPage == 1) {
            mAdpater = new MyAdapter(mData, this);
            mListView.getRefreshableView().setAdapter(mAdpater);
        } else {
            mAdpater.notifyDataSetChanged();
        }


    }

    @Override
    public void showFileMessage() {
        showToast("加载失败");
        mListView.onPullDownRefreshComplete();
        mListView.onPullUpRefreshComplete();
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
