package lx.base.apphall.event_bus.event1;

/**
 * Created by 11300 on 2017/5/8.
 */

public class FirstEvent {
    private String mMsg;
    public FirstEvent(String msg){
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
