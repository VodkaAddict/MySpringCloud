package kled.chen.service;

import kled.chen.StreamConstant;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySender {

    //生产者的Output对应消费者的Input Channel(连接的Topic)
    @Output(StreamConstant.MY_INPUT_CHANNEL)
    MessageChannel output();
}
