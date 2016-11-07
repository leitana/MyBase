package lx.base.apphall.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class JockBody implements Serializable{
    private String allNum;
    private String allPages;
    private List<JockContent> contentlist;

    public String getAllNum() {
        return allNum;
    }

    public void setAllNum(String allNum) {
        this.allNum = allNum;
    }

    public String getAllPages() {
        return allPages;
    }

    public void setAllPages(String allPages) {
        this.allPages = allPages;
    }

    public List<JockContent> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<JockContent> contentlist) {
        this.contentlist = contentlist;
    }
}
