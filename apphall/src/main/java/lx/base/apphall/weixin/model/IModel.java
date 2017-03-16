package lx.base.apphall.weixin.model;

import lx.base.apphall.weixin.OnLoadWeiListener;

/**
 * 创建时间 2017/2/6
 * Created by linxiao.
 */

public interface IModel {
    void loadWei(int page, OnLoadWeiListener listener);
}
