package com.yao.demo;

import com.yao.demo.server.HttpClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private HttpClient httpClient;

	@Test
	void f1(){
		httpClient.f1();
		String url = "http://localhost:8062/mq";
		httpClient.SendPost(null,url);
	}
}
