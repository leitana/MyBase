package lx.base.apphall.recyclierview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;

/**
 * 创建时间 2016/12/12
 * Created by linxiao.
 */

public class SwipeRecyclerAvtivity extends Activity implements NormalRecyclerViewAdapter.IonSlidingViewClickListener, SwipeRefreshLayout.OnRefreshListener {
    private static final int REFRESH_STATUS = 0;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    NormalRecyclerViewAdapter adapter;
    private String TAG = "RecyclerViewDemo";
    private int j = 0;
    private Handler refreshHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_STATUS:
                    //下拉刷新执行的操作，刷新数据
                    mSwipeRefreshLayout.setRefreshing(false);
                    List<String> strings = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        j++;
                        strings.add("测试" + j);
                    }
                    adapter.updateData(strings);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//下面的两种方式自己可以试试看下效果就知道了
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
        //mRecyclerView.setLayoutManager(new     StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        adapter = new NormalRecyclerViewAdapter(this);
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        onRefresh();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(SwipeRecyclerAvtivity.this, "单击" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteBtnCilck(View view, int position) {
        Toast.makeText(SwipeRecyclerAvtivity.this, "删除" + position, Toast.LENGTH_SHORT).show();
        adapter.removeData(position);
    }

    @Override
//下拉刷新的监听
    public void onRefresh() {
        refreshHandler.sendEmptyMessageDelayed(REFRESH_STATUS, 2000);
    }
}
