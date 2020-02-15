package kled.chen.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //创建队列后，若不声明Binding, 默认绑定至默认Exchange(""), RouteKey=队列名称
    @Bean
    public Queue queue1(){
        return new Queue("hello1");
    }

    @Bean
    public Queue queue2(){
        return new Queue("hello2");
    }
}
