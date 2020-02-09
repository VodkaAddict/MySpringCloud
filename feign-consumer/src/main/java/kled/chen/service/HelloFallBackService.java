package kled.chen.service;

import org.springframework.stereotype.Component;

@Component
public class HelloFallBackService implements TestHelloService{
    @Override
    public String getHelloMessage(String name) {
        return "feign fallback";
    }
}
