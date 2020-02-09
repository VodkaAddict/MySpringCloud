/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kled.chen.ribbon.controller;

import kled.chen.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class HelloController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HelloService helloService;

	private static final String SERVICE_NAME = "cloud-eureka-client";

	@GetMapping("/ribbonConsumer")
	public String ribbonConsumer() {
		return restTemplate.getForObject("http://"+ SERVICE_NAME + "/hello", String.class);
	}

	@GetMapping("/hystrixRibbonConsumer/{name}")
	public String hystrixRibbonConsumer(@PathVariable String name) {
		// helloService.helloService(name);
		return helloService.helloService(name);
	}

	@GetMapping("/hystrixRibbonBatch")
	public String testHystrixCollapser() throws ExecutionException, InterruptedException {
		helloService.collapserHelloService("1");
		helloService.collapserHelloService("2");
		helloService.collapserHelloService("3");

//		for (int i = 0; i < 5; i++) {
//			helloService.collapserHelloService(String.valueOf(i));
//		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "success";
	}

	@PutMapping("/hystrixRibbonConsumer/{name}")
	public void update(@PathVariable String name) {
		helloService.updateCache(name);
	}
}
