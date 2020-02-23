package kled.chen.controller;

import kled.chen.service.MySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private MySender mySender;

    @Autowired
    private MessageChannel input;

    @PostMapping("/my-stream-rabbit/{msg}")
    public String mySender(@PathVariable String msg){
        mySender.output().send(MessageBuilder.withPayload(msg).build());
        return "SUCCESS";
    }

    @PostMapping("/rx-my-stream-rabbit/{msg}")
    public String myRxSender(@PathVariable String msg){
        input.send(MessageBuilder.withPayload(msg).build());
        return "RX SUCCESS";
    }
}
