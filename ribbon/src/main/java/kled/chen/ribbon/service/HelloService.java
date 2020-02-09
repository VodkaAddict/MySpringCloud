package kled.chen.ribbon.service;

import com.google.common.base.Joiner;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

@Component
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_NAME = "cloud-eureka-client";

    //一个同一个Http Request生命周期内，相同服务的调用可使用缓存
    @CacheResult
    @HystrixCommand(commandKey = "helloService", fallbackMethod = "helloFallBack")
    public String helloService(@CacheKey String name) {
        return restTemplate.getForObject("http://"+ SERVICE_NAME + "/hello/{1}", String.class, name);
    }

    @HystrixCollapser(batchMethod = "batchHello", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    public Future<String> collapserHelloService(String name) {
        return null;
        //return restTemplate.getForObject("http://"+ SERVICE_NAME + "/hello/{1}", String.class, name);
    }

    @CacheRemove(commandKey = "helloService", cacheKeyMethod = "getHelloCacheKey")
    @HystrixCommand
    public void updateCache(String name){
        //清除缓存后，更新服务端数据
    }

    //服务降级逻辑保护
    private String helloFallBack(String name){
        return "error:" + name;
    }

    //降级服务，并捕获异常
    private String helloFallBackCatchThrowable(Throwable throwable){
        return "error-catch-throwable:" + throwable.getMessage();
    }

    //缓存key, 优先级高于@CacheKey
    //不指定CacheKeyMethod会使用所有参数作为Key
    private String getHelloCacheKey(String name){
        return name;
    }

    @HystrixCommand
    private List<String> batchHello(List<String> names){
        return restTemplate.getForObject("http://"+ SERVICE_NAME + "/batch?names={1}", List.class, Joiner.on(",").join(names));
    }
}
