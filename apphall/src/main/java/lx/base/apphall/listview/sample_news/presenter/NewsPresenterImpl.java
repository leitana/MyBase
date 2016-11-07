package lx.base.apphall.listview.sample_news.presenter;

import java.util.List;

import lx.base.apphall.beans.JockContent;
import lx.base.apphall.listview.sample_news.model.INewsModel;
import lx.base.apphall.listview.sample_news.model.NewsModellmpl;
import lx.base.apphall.listview.sample_news.view.INewsView;

/**
 * 创建时间 2016/9/12
 * Created by linxiao.
 */
public class NewsPresenterImpl implements NewsPresenter, NewsModellmpl.OnLoadNewsListener{
    private INewsView mNewsView;
    private INewsModel mNewsModel;

    public NewsPresenterImpl(INewsView newsView) {
        this.mNewsView = newsView;
        mNewsModel = new NewsModellmpl();
    }
    @Override
    public void loadNews(int page) {
        mNewsView.showProgress();
        mNewsModel.loadNews(page, this);
    }

    @Override
    public void onSuccess(List<JockContent> list) {
        mNewsView.hideProgress();
        mNewsView.addNews(list);
        }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showFileMessage();
    }
}
