package lx.base.apphall.weixin.view;

import lx.base.apphall.weixin.beans.WContent;

/**
 * 创建时间 2017/2/6
 * Created by linxiao.
 */

public interface IView {
    void showProgress();

    void hideProgress();

    void addNews(WContent content);

    void showFileMessage(String message);
}
