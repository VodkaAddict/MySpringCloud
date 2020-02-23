package kled.chen.service;

import kled.chen.StreamConstant;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MyStreamListener {

    @StreamListener(StreamConstant.MY_INPUT_CHANNEL)
    @SendTo(StreamConstant.MY_OUTPUT_CHANNEL) //返回消息至MY_OUTPUT_CHANNEL隧道
    public String message(String msg){
        System.out.println("MyStreamListener msg=" + msg);
        return "this is a reply message from " + msg;
    }

    @StreamListener(StreamConstant.MY_OUTPUT_CHANNEL)
    public void replyMessage(String msg){
        System.out.println("MyStreamListener reply msg=" + msg);
    }
}
