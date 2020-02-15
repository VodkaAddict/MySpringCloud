package kled.chen.extensions;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class MyRemoteApplicationEvent extends RemoteApplicationEvent {

    private String message;

    public MyRemoteApplicationEvent(Object source, String originService, String message) {
        super(source, originService);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
