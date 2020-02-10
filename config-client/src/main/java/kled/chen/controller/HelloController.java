package kled.chen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class HelloController {

    @Value("${from:undefined}")
    private String from;

    @Autowired
    private Environment env;

    @GetMapping("configClient1")
    public String configCLient1(){
        return from;
    }

    @GetMapping("configClient2")
    public String configCLient2(){
        return env.getProperty("from", "undefined");
    }
}
