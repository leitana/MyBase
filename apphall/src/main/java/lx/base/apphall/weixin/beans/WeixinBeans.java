package lx.base.apphall.weixin.beans;

/**
 * 创建时间 2017/2/6
 * Created by linxiao.
 */

public class WeixinBeans {
    private String reason;
    private WContent result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public WContent getResult() {
        return result;
    }

    public void setResult(WContent result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
