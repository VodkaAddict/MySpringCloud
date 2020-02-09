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

package kled.chen.eurekaClient.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private DiscoveryClient client;

	@GetMapping(value = "/hello")
	public String getHelloMessage() {
		//ServiceInstance serviceInstance = client.get
		System.out.println(client);
		return "Hello!";
	}

	@GetMapping(value = "/hello/{name}")
	public String getHelloMessageWithName(@PathVariable String name) {
		logger.info("getHelloMessageWithName -> name={}",name);
		return "Hello!" + name;
	}

	@GetMapping(value = "/batch")
	public List<String> getBatchHelloMessageWithName(String names) {
		logger.info("getBatchHelloMessageWithName -> name={}", names);
		return CollectionUtils.arrayToList(names.split(","));
	}
}
