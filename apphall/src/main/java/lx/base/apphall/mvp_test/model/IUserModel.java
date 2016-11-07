package lx.base.apphall.mvp_test.model;

import lx.base.apphall.mvp_test.bean.UserBean;

/**
 * 创建时间 2016/9/6
 * Created by Administrator.
 * model需要对id，FirstName LastName进行读写
 */
public interface IUserModel {
    void setID(int id);
    void setFirstName(String firstName);
    void setLastName(String lastName);
    UserBean load(int id);//通过id获取user信息，返回一个UserBean
}
