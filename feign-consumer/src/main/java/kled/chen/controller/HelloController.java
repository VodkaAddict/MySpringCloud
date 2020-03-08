package kled.chen.controller;

import kled.chen.service.RefactorHelloService;
import kled.chen.service.TestHelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private TestHelloService helloService;

    @Autowired
    private RefactorHelloService refactorHelloService;

    @GetMapping("/feign-consumer/{name}")
    public String feignConsumer(@PathVariable String name){
        logger.info("feignConsumer name={}", name);
        return helloService.getHelloMessage(name);
    }

    @GetMapping("/feign-consumer1/{name}")
    public String feignConsumer1(@PathVariable String name){
        return refactorHelloService.getHelloMessageWithName(name);
    }

    @GetMapping("/feign-consumer2")
    public String feignConsumer1(){
        return refactorHelloService.getHelloMessage();
    }
}
