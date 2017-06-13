package lx.base.apphall.MPAndroidCharts.beans;

import java.io.Serializable;

/**
 * Created by 11300 on 2017/6/12.
 */

public class BarBeans implements Serializable{
    private float value;
    private String xName;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getxName() {
        return xName;
    }

    public void setxName(String xName) {
        this.xName = xName;
    }
}
