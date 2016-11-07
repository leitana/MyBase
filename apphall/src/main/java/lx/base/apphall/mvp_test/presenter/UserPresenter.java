package lx.base.apphall.mvp_test.presenter;

import lx.base.apphall.mvp_test.bean.UserBean;
import lx.base.apphall.mvp_test.model.IUserModel;
import lx.base.apphall.mvp_test.model.UserModel;
import lx.base.apphall.mvp_test.view.IUserView;

/**
 * 创建时间 2016/9/6
 * Created by linxiao.
 */
public class UserPresenter {
    private IUserView mUserView;
    private IUserModel mUserModel;

    public UserPresenter(IUserView view) {
        mUserView = view;
        mUserModel = new UserModel();
    }

    public void saveUser(int id, String firstName, String lastName) {
        mUserModel.setFirstName(firstName);
        mUserModel.setLastName(lastName);
        mUserModel.setID(id);
    }

    public void loadUser(int id) {
        UserBean userBean = mUserModel.load(id);
        mUserView.setFirstName(userBean.getFirstName());
        mUserView.setLastName(userBean.getLastName());
    }
}
