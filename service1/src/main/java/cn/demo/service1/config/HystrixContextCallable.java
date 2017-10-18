package cn.demo.service1.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.slf4j.MDC;

/**
 * Created by zhengwei on 2017/10/18.
 */
public class HystrixContextCallable implements Callable {

    private final Callable actual;
    private final Map parentMDC;

    public HystrixContextCallable(Callable actual) {
        this.actual = actual;
        this.parentMDC = MDC.getCopyOfContextMap();
    }

    @Override
    public Object call() throws Exception {
        Map childMDC = MDC.getCopyOfContextMap();
        try {
            MDC.setContextMap(parentMDC);
            return actual.call();
        } finally {
            if(childMDC==null){
                childMDC = new HashMap();
            }
            MDC.setContextMap(childMDC);
        }
    }
}
