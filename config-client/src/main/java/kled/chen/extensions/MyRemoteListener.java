package kled.chen.extensions;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyRemoteListener implements ApplicationListener<MyRemoteApplicationEvent> {

    @Override
    public void onApplicationEvent(MyRemoteApplicationEvent event) {
        System.out.println("MyRemoteListener: content=" + event.getMessage());
    }
}
