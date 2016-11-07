package lx.base.apphall.listview.sample_news.model;

/**
 * 创建时间 2016/9/12
 * Created by linxiao.
 */
public interface INewsModel {
    void loadNews(int page, NewsModellmpl.OnLoadNewsListener listener);
}
