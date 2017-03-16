package lx.base.apphall.weixin.presenter;

import lx.base.apphall.weixin.OnLoadWeiListener;
import lx.base.apphall.weixin.beans.WeixinBeans;
import lx.base.apphall.weixin.model.ModelImpl;
import lx.base.apphall.weixin.view.ViewImpl;

/**
 * 创建时间 2017/2/7
 * Created by linxiao.
 */

public class PresenterImpl implements IPresenter, OnLoadWeiListener {
    private ModelImpl model;
    private ViewImpl view;

    public PresenterImpl(ViewImpl viewImpl) {
        view = viewImpl;
        model = new ModelImpl();
    }

    @Override
    public void loadWei(int page) {
        view.showProgress();
        model.loadWei(page, this);
    }

    @Override
    public void onSuccess(WeixinBeans weixinBeans) {
        view.hideProgress();
        view.addNews(weixinBeans.getResult());
    }

    @Override
    public void onFailure(String msg, Throwable e) {
        view.hideProgress();
        view.showFileMessage(msg);
    }
}
