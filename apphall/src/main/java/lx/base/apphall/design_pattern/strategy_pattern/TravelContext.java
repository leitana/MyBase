package lx.base.apphall.design_pattern.strategy_pattern;

/**
 * 策略包装类
 * Created by 11300 on 2018/4/8.
 */

public class TravelContext {
    Strategy strategy;

    public Strategy getStrategy(){
        return strategy;
    }

    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public String travel() {
        if (strategy != null){
            return strategy.travel();
        } else {
            return "---";
        }
    }
}
