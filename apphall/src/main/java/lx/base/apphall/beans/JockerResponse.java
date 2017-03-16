package lx.base.apphall.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/22.
 */
public class JockerResponse implements Serializable{
    private String error_code;
    private String reason;
    private JockBody result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JockBody getResult() {
        return result;
    }

    public void setResult(JockBody result) {
        this.result = result;
    }
}
