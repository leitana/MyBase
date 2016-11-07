package lx.base.apphall.callback;

/**
 * 回调接口
 * Created by Administrator on 2016/8/16.
 */
public interface CallBack {
    /**
     * B 知道答案要告诉 A，也就是回调函数
     * @param result
     */
    public void solve(String result);
}
