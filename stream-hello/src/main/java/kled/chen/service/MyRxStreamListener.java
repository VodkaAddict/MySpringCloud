package kled.chen.service;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.annotation.rxjava.EnableRxJavaProcessor;
import org.springframework.cloud.stream.annotation.rxjava.RxJavaProcessor;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;

@EnableRxJavaProcessor
public class MyRxStreamListener {

    @Bean
    public RxJavaProcessor<String, String> processor(){
        return inputStream -> inputStream.map(data -> {
            System.out.println("MyRxStreamListener msg=" + data);
            return data;
        }).map(data -> String.valueOf("his is a reply rx message from " + data));
    }

    @StreamListener(Processor.OUTPUT)
    public void replyMessage(String msg){
        System.out.println("MyStreamListener reply msg=" + msg);
    }
}
