package kled.chen.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "hello2")
public class HelloListener2 {

    //消费监听同一个队列，根据不同的消息格式进行消费
    @RabbitHandler
    public void consumer1(String str){
        System.out.println("this is listener1, message=" + str);
    }

    @RabbitHandler
    public void consumer2(Map<String,String> map){
        System.out.println("this is listener2, message=" + map);
    }
}
