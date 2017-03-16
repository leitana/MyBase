package lx.base.apphall.listview.sample_news.model;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import lx.base.apphall.beans.JockContent;
import lx.base.apphall.beans.JockerResponse;

/**
 * 创建时间 2016/9/12
 * Created by linxiao.
 * http://japi.juhe.cn/joke/img/text.from?key=6ca96a33f79dd59f6e66cfd507c6352b&page=1&pagesize=10
 */
public class NewsModellmpl implements INewsModel{
    private String httpUrl = "http://japi.juhe.cn/joke/content/text.from";
    private String httpArg = "page=";
    private String key = "6ca96a33f79dd59f6e66cfd507c6352b";
    private String Url;
    private List<JockContent> mData;
    private int nowPage = 1;
    private int pageSize = 20;
    private Gson gson = new Gson();
    @Override
    public void loadNews(int page, final OnLoadNewsListener listener) {
        OkHttpUtils
                .get()
                .url(httpUrl)
                .addParams("key", key)
                .addParams("page", page + "")
                .addParams("pagesize", pageSize + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Logger.d("有错");
                    }

                    @Override
                    public void onResponse(String response) {
                        Logger.d(response);
                        JockerResponse jockerResponse = gson.fromJson(response, JockerResponse.class);
                        mData = new ArrayList<>();
                        mData.addAll(jockerResponse.getResult().getData());
                        listener.onSuccess(mData);
                    }
                });
    }

    public interface OnLoadNewsListener {
        void onSuccess(List<JockContent> list);
        void onFailure(String msg, Exception e);
    }
}
