package kled.chen.ribbon.service;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

public class HystrixHelloCommand extends HystrixCommand<String> {

    private String str;

    public HystrixHelloCommand(String str) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloGroup")).
                andCommandKey(HystrixCommandKey.Factory.asKey("hello")).
                andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("helloThreadTool")));
        this.str = str;
    }

    @Override
    protected String run() throws Exception {
        //封装的服务
        return "hello";
    }

    @Override
    protected String getFallback() {
        //服务降级
        return "error";
    }

    @Override
    protected String getCacheKey() {
        //请求缓存
        return "Hello";
    }

    //缓存清理
    public static void flushCache(){
        HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey("hello"), HystrixConcurrencyStrategyDefault.getInstance()).clear("Hello");
    }
}
