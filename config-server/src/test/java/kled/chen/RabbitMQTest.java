package kled.chen;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class RabbitMQTest extends SpringTestConfigServerApplicationTests {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void send1() throws InterruptedException {
        rabbitTemplate.convertAndSend("hello1", "hello world!");
        Thread.sleep(10000);
    }

    @Test
    public void send2() throws InterruptedException {
        Map<String, String> param = Maps.newHashMap();
        param.put("hello", "world");
        rabbitTemplate.convertAndSend("hello2", param);

        rabbitTemplate.convertAndSend("hello2", "hello kled!!!");
        Thread.sleep(10000);
    }
}
