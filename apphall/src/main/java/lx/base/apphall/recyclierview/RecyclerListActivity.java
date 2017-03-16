package lx.base.apphall.recyclierview;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx.base.apphall.beans.JockContent;
import lx.base.apphall.beans.JockerResponse;
import lx_base.mybase.common.base.BaseActionBarActivity;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建时间 2016/10/27
 * Created by linxiao.
 */

public class RecyclerListActivity extends BaseActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshWidget;

    private int currentpage;
    private View mRootView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerAdapter mAdapter;

    private List<JockContent> mData;
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                currentpage = currentpage + 1;
                getData(currentpage);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }
    };
    private RecyclerAdapter.OnItemClickListener onItemClickListener = new RecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            showToast(position + "");
        }
    };

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.recycler_list_activity, null);
        ButterKnife.bind(this, mRootView);
        Logger.init();
        return mRootView;
    }

    @Override
    protected void initParm() {
        super.initParm();
        currentpage = 1;
        mData = new ArrayList<>();
    }

    @Override
    protected void initView() {
        super.initView();
        mSwipeRefreshWidget.setColorSchemeResources(R.color.blue,
                R.color.lite_blue, R.color.green,
                R.color.primary_light);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(RecyclerListActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecyclerAdapter(this, mData);
        mAdapter.setOnItemClickListener(onItemClickListener);

        mRecyclerView.addOnScrollListener(onScrollListener);
        onRefresh();
    }

    private void getData(int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://japi.juhe.cn/joke/content/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ReMovieService service = retrofit.create(ReMovieService.class);
        service.getJock("6ca96a33f79dd59f6e66cfd507c6352b", page, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JockerResponse>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("onCompleted----------------" + Thread.currentThread().getName());
                        if (mSwipeRefreshWidget.isRefreshing()) {
                            mSwipeRefreshWidget.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("onError----------------" + Thread.currentThread().getName() + e.getMessage());
                        showToast("加载失败");
                        if (mSwipeRefreshWidget.isRefreshing()) {
                            mSwipeRefreshWidget.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onNext(JockerResponse jockerResponse) {
                        Logger.d("onNext----------------" + Thread.currentThread().getName());
                        mAdapter.isShowFooter(true);
                        if(mData == null) {
                            mData = new ArrayList<JockContent>();
                        }
                        mData.addAll(jockerResponse.getResult().getData());
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onRefresh() {
        currentpage = 1;
        getData(currentpage);
        mRecyclerView.setAdapter(mAdapter);
    }
}
