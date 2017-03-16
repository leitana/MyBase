package lx.base.apphall.weixin;

import lx.base.apphall.weixin.beans.WeixinBeans;

/**
 * 创建时间 2017/2/7
 * Created by linxiao.
 */

public interface OnLoadWeiListener {
    void onSuccess(WeixinBeans weixinBeans);

    void onFailure(String msg, Throwable e);
}
