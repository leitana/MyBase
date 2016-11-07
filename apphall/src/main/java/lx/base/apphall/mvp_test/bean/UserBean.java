package lx.base.apphall.mvp_test.bean;

/**
 * 创建时间 2016/9/6
 * Created by Administrator.
 */
public class UserBean {
    private String mFirstName ;
    private String mLastName ;
    public UserBean (String firstName, String lastName) {
        this .mFirstName = firstName;
        this .mLastName = lastName;
    }
    public String getFirstName() {
        return mFirstName ;
    }
    public String getLastName() {
        return mLastName ;
    }
}
