package lx.base.apphall.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class JockBody implements Serializable{
    private List<JockContent> data;

    public List<JockContent> getData() {
        return data;
    }

    public void setData(List<JockContent> data) {
        this.data = data;
    }
}
