package lx.base.apphall.weixin.beans;

import java.util.List;

/**
 * 创建时间 2017/2/6
 * Created by linxiao.
 */

public class WContent {
    private List<WList> list;
    private int totalPage;
    private int ps;
    private int pno;

    public List<WList> getList() {
        return list;
    }

    public void setList(List<WList> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }
}
