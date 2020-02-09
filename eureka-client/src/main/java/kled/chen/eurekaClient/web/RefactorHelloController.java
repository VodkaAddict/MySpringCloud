package kled.chen.eurekaClient.web;

import kled.chen.service.HelloService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactorHelloController implements HelloService {

    @Override
    public String getHelloMessage() {
        return "hello world!!!";
    }

    @Override
    public String getHelloMessageWithName(@PathVariable String name) {
        return "hello world!!!" + name;
    }
}
