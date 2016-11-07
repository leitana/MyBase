package lx.base.apphall.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/22.
 */
public class JockContent implements Serializable{
    private String ct;
    private String text;
    private String title;
    private String type;

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
