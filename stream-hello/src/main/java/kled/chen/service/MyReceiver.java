package kled.chen.service;

import kled.chen.StreamConstant;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MyReceiver {

    @Input(StreamConstant.MY_OUTPUT_CHANNEL)
    SubscribableChannel input();
}
