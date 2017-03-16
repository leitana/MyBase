package lx.base.apphall.weixin.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx.base.apphall.weixin.beans.WContent;
import lx.base.apphall.weixin.beans.WList;
import lx.base.apphall.weixin.presenter.PresenterImpl;
import lx_base.mybase.common.base.AdapterBase;
import lx_base.mybase.common.base.BaseActionBarActivity;
import lx_base.mybase.common.util.ImageLoaderUtils;
import lx_base.mybase.common.widgets.pull_refresh.PullToRefreshBase;
import lx_base.mybase.common.widgets.pull_refresh.PullToRefreshListView;

/**
 * 创建时间 2017/2/7
 * Created by linxiao.
 */

public class ViewImpl extends BaseActionBarActivity implements IView {
    @BindView(R.id.lv_news)
    PullToRefreshListView mListView;
    private View mRootView;
    private List<WList> mData;
    private int mCurrentPage = 1;
    private int totalPage;
    private MyAdapter mAdpater;
    private PresenterImpl presenter;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.news_activity, null);
        ButterKnife.bind(this, mRootView);
        setMyActionBarTitle("微信精选");
        presenter = new PresenterImpl(this);
        mListView.getRefreshableView().setDivider(null);
        mListView.setPullLoadEnabled(true);
        mListView.setPullRefreshEnabled(true);
        mListView.setHasMoreData(true);
        presenter.loadWei(1);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mData != null) {
                    if (mData.size() > 0) {
                        mData.clear();
                    }
                }
                presenter.loadWei(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                if (mRequestTotal / 20 > 0) {
//                    mRequestTotal -= 20;
//                    mCurrentPage = mCurrentPage + 1;
//                    newsPresenter.loadNews(mCurrentPage);
//                } else {
//                    mListView.onPullUpRefreshComplete();
//                }
                if (mCurrentPage < totalPage) {
                    presenter.loadWei(mCurrentPage);
                } else {
                    mListView.onPullUpRefreshComplete();
                }
            }
        });
        mListView.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ViewImpl.this, WebView.class);
                Bundle bundle = new Bundle();
                bundle.putString("herf", mData.get(position).getUrl());
                bundle.putString("title", mData.get(position).getTitle());
                intent.putExtras(bundle);
                startActivity(intent);
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
    public void addNews(WContent content) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mCurrentPage = content.getPno();
        totalPage = content.getTotalPage();
        mData.addAll(content.getList());
        if (mCurrentPage == 1) {
            mAdpater = new MyAdapter(mData, this);
            mListView.getRefreshableView().setAdapter(mAdpater);
        } else {
            mAdpater.notifyDataSetChanged();
        }
    }

    @Override
    public void showFileMessage(String message) {
        showToast(message);
        mListView.onPullDownRefreshComplete();
        mListView.onPullUpRefreshComplete();
    }

    class MyAdapter extends AdapterBase<WList> {

        protected MyAdapter(List<WList> dataList, Context context) {
            super(dataList, context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            WList now = getItem(position);
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_weixin, parent, false);
            }
            ImageView imageView = obtainViewFromViewHolder(convertView, R.id.iv_url);
            TextView title = obtainViewFromViewHolder(convertView, R.id.tv_title);
            TextView from = obtainViewFromViewHolder(convertView, R.id.tv_from);
//            if (now.getFirstImg() != null) {
            ImageLoaderUtils.display(ViewImpl.this, imageView, now.getFirstImg());
//            }
//            if (now.getTitle() != null)
            title.setText(now.getTitle());
            from.setText(now.getSource());
            return convertView;
        }
    }
}
