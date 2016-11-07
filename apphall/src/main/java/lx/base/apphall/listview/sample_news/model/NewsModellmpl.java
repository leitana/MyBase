package lx.base.apphall.listview.sample_news.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import lx.base.apphall.beans.JockContent;
import lx.base.apphall.beans.JockerResponse;
import lx_base.mybase.common.util.OkHttpUtils;

/**
 * 创建时间 2016/9/12
 * Created by linxiao.
 */
public class NewsModellmpl implements INewsModel{
    private String httpUrl = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text";
    private String httpArg = "page=";
    private String Url;
    private List<JockContent> mData;
    @Override
    public void loadNews(int page, final OnLoadNewsListener listener) {
        Url = httpUrl + "?" + httpArg + page;
        final Gson gson = new Gson();
        OkHttpUtils.ResultCallback<String> loadJockCallBack = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                JockerResponse jockerResponse = gson.fromJson(response, JockerResponse.class);
                mData = new ArrayList<>();
                mData.addAll(jockerResponse.getShowapi_res_body().getContentlist());
                listener.onSuccess(mData);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("加载失败", e);
            }
        };
        OkHttpUtils.getWithHead(Url, loadJockCallBack, "apikey","529cad5d92f049c0165be5fdfbf210b8");
    }

    public interface OnLoadNewsListener {
        void onSuccess(List<JockContent> list);
        void onFailure(String msg, Exception e);
    }
}
