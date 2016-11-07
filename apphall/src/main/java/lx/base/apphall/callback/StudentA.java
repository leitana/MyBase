package lx.base.apphall.callback;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/8/16.
 */
public class StudentA implements CallBack{
    /**
     * Student2对象的引用
     */
    public void askQuestion(final String question) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Logger.d("A问2一个问题");
                Student2.executeMessage(StudentA.this, question);
            }
        }).start();
        play();
    }
    private void play() {
        Logger.d("我要去玩耍");
    }

    /**
     * 提供回调方法的实现
     * @param result
     */
    @Override
    public void solve(String result) {
        //dosomething
        Logger.d(result);
    }
}
