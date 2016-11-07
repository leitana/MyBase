package lx.base.apphall.callback;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/8/16.
 */
public class Student2 {
    public static void executeMessage(CallBack callBack, String question) {
        Logger.d("StudentA的问题" + question);
        /**
         * 模拟Student2做自己的事情需要时间
         */
        for (int i = 0; i < 1000; i++) {

        }
        String result = "答案是2";
        /**
         * Studet2将想到的答案告诉StudentA
         * 相当于Student2类调用StudentA类中的方法
         */
        callBack.solve(result);
    }
}
