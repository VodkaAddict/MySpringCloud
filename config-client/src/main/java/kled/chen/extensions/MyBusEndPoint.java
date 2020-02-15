package kled.chen.extensions;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.cloud.bus.endpoint.AbstractBusEndpoint;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Endpoint(
        id = "bus-hello"
)
@Component
public class MyBusEndPoint implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @WriteOperation
    public void busHello(@RequestParam String msg) {
        System.out.println("*****************busHello**************");
        applicationEventPublisher.publishEvent(new MyRemoteApplicationEvent(this, "MyBusEndPoint", msg));
    }
}
