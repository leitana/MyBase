package lx.base.apphall.weixin.model;

import com.orhanobut.logger.Logger;

import lx.base.apphall.weixin.OnLoadWeiListener;
import lx.base.apphall.weixin.beans.WeixinBeans;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建时间 2017/2/6
 * Created by linxiao.
 */

public class ModelImpl implements IModel {
    private String url = "http://v.juhe.cn/";
    private int pageSize = 20;

    @Override
    public void loadWei(int page, OnLoadWeiListener listener) {
        getData(page, listener);
    }

    private void getData(int nowPage, final OnLoadWeiListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ReWeiService service = retrofit.create(ReWeiService.class);
        service.getWei("36e7ea1662a37d50737f248edc641f7a", nowPage, pageSize, "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeixinBeans>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure("There is a problem", e);
                    }

                    @Override
                    public void onNext(WeixinBeans weixinBeans) {
                        Logger.d(weixinBeans);
                        listener.onSuccess(weixinBeans);
                    }
                });
    }
}
