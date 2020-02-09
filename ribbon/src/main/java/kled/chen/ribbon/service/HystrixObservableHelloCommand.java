package kled.chen.ribbon.service;

import com.netflix.hystrix.*;
import rx.Observable;

public class HystrixObservableHelloCommand extends HystrixObservableCommand<String> {

    private String str;

    public HystrixObservableHelloCommand(String str) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloGroup")).
                andCommandKey(HystrixCommandKey.Factory.asKey("hello")));
        this.str = str;
    }


    @Override
    protected Observable<String> construct() {
        return Observable.create(subscriber -> {
            subscriber.onNext("hello");
            subscriber.onCompleted();
        });
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        return super.resumeWithFallback();
    }
}
