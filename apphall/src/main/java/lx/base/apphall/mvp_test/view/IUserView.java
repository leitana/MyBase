package lx.base.apphall.mvp_test.view;

/**
 * 创建时间 2016/9/6
 * Created by linxiao.
 * 一个登陆的mvp例子
 * 根据需求所知，View可以对ID、FirstName、LastName这三个EditView进行读操作
 * 对FirstName、LastName进行写操作
 */
public interface IUserView {
    int getID();
    String getFirstName();
    String getLastName();
    void setFirstName(String firstName);
    void setLastName(String lastName);
}
