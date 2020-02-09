package kled.chen.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cloud-eureka-client")
public interface RefactorHelloService extends HelloService {
}
