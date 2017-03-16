package lx.base.apphall.demo.beans;


import java.io.Serializable;
import java.util.List;

/**
 * 创建时间 2017/2/18
 * Created by linxiao.
 */

public class AddViewBean implements Serializable {
    private String name;
    private String cid;
    private String place;
    private List<Photo> photos;
    private List<TestResult> testResults;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
    }
}
