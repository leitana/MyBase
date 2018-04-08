package lx.base.apphall.rxjava;

import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;
import lx_base.mybase.common.util.ImageLoaderUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建时间 2016/11/3
 * Created by linxiao.
 */

public class RxJavaTestActivity extends BaseActionBarActivity {
    @BindView(R.id.albm)
    GridView albm;
    private View mRootView;

    private List<String> mData;
    private String path;

    private MyAdapter mAdapter;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.show_album_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initParm() {
        super.initParm();
        mData = new ArrayList<String>();
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/";
    }

    @Override
    protected void initView() {
        super.initView();
        getPic();
    }

    private void getPic() {
        Observable.create((Observable.OnSubscribe<List<String>>) subscriber -> {
            File file = new File(path);
            File[] subFile = file.listFiles();
            for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
                if (!subFile[iFileLength].isDirectory()) {
                    String filename = subFile[iFileLength].getName();
                    if (filename.trim().toLowerCase().endsWith(".jpg")) {
                        mData.add(path + filename);
                    }
                }
            }
            subscriber.onNext(mData);
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("加载完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.getMessage());
                    }

                    @Override
                    public void onNext(List<String> s) {
                        Logger.d("onNext");
                        if (s.size() > 0) {
                            mAdapter = new MyAdapter(s);
                            albm.setAdapter(mAdapter);
                        } else {
                            Snackbar.make(mRootView, "无数据", Snackbar.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private static class ViewHolder {
        ImageView imageView;
    }

    public class MyAdapter extends BaseAdapter {
        private List<String> data;

        public MyAdapter(List<String> data) {
            this.data = new ArrayList<String>();
            this.data= data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewholder = null;
            if (convertView == null) {
                viewholder = new ViewHolder();
                convertView = LayoutInflater.from(RxJavaTestActivity.this).inflate(R.layout.album_item, null);
                viewholder.imageView = (ImageView) convertView.findViewById(R.id.iv_item_image);
                convertView.setTag(viewholder);
            } else {
                viewholder = (ViewHolder) convertView.getTag();
            }
            ImageLoaderUtils.display(RxJavaTestActivity.this, viewholder.imageView, data.get(position));
            return convertView;
        }
    }

}
