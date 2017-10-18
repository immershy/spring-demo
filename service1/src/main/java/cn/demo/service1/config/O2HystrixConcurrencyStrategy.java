package cn.demo.service1.config;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import java.util.concurrent.Callable;

/**
 * Created by zhengwei on 2017/10/18.
 */
public class O2HystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    @Override
    public Callable wrapCallable(Callable callable) {
        return new HystrixContextCallable(callable);
    }
}
