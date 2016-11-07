package lx.base.apphall.listview.sample_news.view;

import java.util.List;

import lx.base.apphall.beans.JockContent;

/**
 * 创建时间 2016/9/12
 * Created by linxiao.
 */
public interface INewsView {
    void showProgress();
    void hideProgress();
    void addNews(List<JockContent> mData);
    void showFileMessage();
}
