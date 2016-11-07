package lx.base.apphall.recyclierview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * Created by Administrator on 2016/8/5.
 */
public class RecyclerViewActivity extends BaseActionBarActivity {
    @BindView(R.id.recycler_list)
    RecyclerView recyclerView;
    private View mRootView;
    private List<String> mFirstData;
    private List<String> mSecondData;

    public enum ITEM_TYPE {
        ITEM_TYPE_IMAGE, ITEM_TYPE_TEXT
    }

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.recyclerview_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initParm() {
        super.initParm();
        mFirstData = new ArrayList<String>();
        mSecondData = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mFirstData.add("RecyclerView" + i);
            mSecondData.add("RecyclerView" + i + i);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        showMyRightImageView2();
        setMyRightImageView2(R.mipmap.more);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置垂直布局
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new FirstAdapter(RecyclerViewActivity.this, mFirstData));
    }

    @Override
    public void clickMyRightImageView2() {
        super.clickMyRightImageView2();
        showPopupWindow(getMyRightImageView2());
    }

    private void showPopupWindow(View view) {
        View contentView = getLayoutInflater().inflate(R.layout.popwindow_layout, null);
        final TextView textView = (TextView) contentView.findViewById(R.id.tv_pop);
        textView.setText("recycler2");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getText().equals("recycler2")) {
                    textView.setText("recycler1");
                    recyclerView.setAdapter(new SecondAdapter(RecyclerViewActivity.this, mSecondData));
                } else {
                    textView.setText("recycler2");
                    recyclerView.setAdapter(new FirstAdapter(RecyclerViewActivity.this, mFirstData));
                }
            }
        });

        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.pop_bg));
        popupWindow.showAsDropDown(view);
    }

    class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.myViewHolder> {
        private List<String> mDatas;
        private Context context;
        private LayoutInflater inflater;

        public FirstAdapter(Context context, List<String> datas) {
            this.context = context;
            this.mDatas = datas;
            inflater = LayoutInflater.from(this.context);
        }

        @Override
        public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.recycler_text_item, parent, false);
            myViewHolder viewHolder = new myViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(myViewHolder holder, int position) {
            holder.textView1.setText(mDatas.get(position));
        }


        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class myViewHolder extends RecyclerView.ViewHolder{
            TextView textView1;

            public myViewHolder(View itemView) {
                super(itemView);
                textView1 = (TextView) itemView.findViewById(R.id.tv_recycler_item1);
            }
        }
    }


    class SecondAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<String> mDatas;
        private Context context;
        private LayoutInflater inflater;

        public SecondAdapter(Context context, List<String> datas) {
            this.context = context;
            this.mDatas = datas;
            inflater = LayoutInflater.from(this.context);
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_TEXT.ordinal() : ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof myTextViewHolder) {
                ((myTextViewHolder) holder).textView1.setText(mDatas.get(position));
            } else if (holder instanceof myImageViewHolder) {
                ((myImageViewHolder) holder).textView2.setText(mDatas.get(position));
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_TYPE.ITEM_TYPE_TEXT.ordinal()) {
                View view = inflater.inflate(R.layout.recycler_text_item, parent, false);
                myTextViewHolder viewHolder = new myTextViewHolder(view);
                return viewHolder;
            } else {
                View view = inflater.inflate(R.layout.recycler_image_item, parent, false);
                myImageViewHolder viewHolder = new myImageViewHolder(view);
                return viewHolder;
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class myTextViewHolder extends RecyclerView.ViewHolder{
            TextView textView1;

            public myTextViewHolder(final View itemView) {
                super(itemView);
                textView1 = (TextView) itemView.findViewById(R.id.tv_recycler_item1);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        showToast("item类型1");
                        showSnackbar(itemView, "item类型1");
                    }
                });
            }
        }
        class myImageViewHolder extends RecyclerView.ViewHolder{
            TextView textView2;

            public myImageViewHolder(final View itemView) {
                super(itemView);
                textView2 = (TextView) itemView.findViewById(R.id.tv_recycler_item2);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        showToast("item类型2");
                        showSnackbar(itemView, "item类型2");
                    }
                });
            }
        }
    }
}
