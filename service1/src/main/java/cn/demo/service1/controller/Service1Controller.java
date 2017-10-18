package cn.demo.service1.controller;

import cn.demo.AbstractController;
import cn.demo.service1.client.Service0Client;
import cn.demo.service1.config.O2HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @Author: Leo
 * @Blog: http://blog.csdn.net/lc0817
 * @CreateTime: 2017/1/19 14:21
 * @Description:
 */
@RestController
public class Service1Controller extends AbstractController {
    @Autowired
    Service0Client service0Client;

    static {
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new O2HystrixConcurrencyStrategy());
    }

    @GetMapping("/test/{province}")
    public String test(
            @PathVariable String province
    ) {
        HystrixRequestContext.initializeContext();
        MDC.put("province",province);
        return service0Client.test(province);
    }

}
