package kled.chen.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloListener1 {

    @RabbitListener(queues = "hello1")
    public void consumer(String str){
        System.out.println("this is listener1, message=" + str);
    }
}
