package lx.base.apphall.callback;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/8/16.
 */
public class Test extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StudentA a = new StudentA();
        a.askQuestion("1+1 = ?");
    }
}
