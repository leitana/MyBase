package lx.base.apphall.design_pattern.strategy_pattern;

/**
 * Created by 11300 on 2018/4/8.
 */

public class WalkStrategy implements Strategy{
    @Override
    public String travel() {
        return "---------------walk";
    }
}
