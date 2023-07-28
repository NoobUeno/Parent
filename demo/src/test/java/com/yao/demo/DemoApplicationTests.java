package com.yao.demo;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yao.common.entity.User;
import com.yao.demo.server.HttpClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private HttpClient httpClient;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	void f1(){
		httpClient.f1();
		String url = "http://localhost:8062/mq";
		httpClient.SendPost(null,url);
	}

	@Test
	void f2(){

		List<User> userList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			String a = "test"+i;
			User user = new User();
			user.setId(a);
			userList.add(user);
			redisTemplate.opsForList().leftPush("userList",user);
		}

		List<User> users = redisTemplate.opsForList().range("userList",0,-1);
		for (int i = 0; i < users.size(); i++) {
//			redisTemplate.opsForList().leftPop("userList");
			User user = new User();
			user.setId("123");
			redisTemplate.opsForList().set("userList",i,user);
		}
		List<User> userList1 = redisTemplate.opsForList().range("userList",0,-1);

		System.out.println(userList1);
//		System.out.println(users.get(5));
//		redisTemplate.opsForList().remove("userList",1,users.get(5));
//		System.out.println(redisTemplate.opsForList().range("userList",0,-1));
//		User u = (User) redisTemplate.opsForList().index("userList",5);
//		System.out.println(u);
//		List<User> list = new ArrayList<>();
//		User user = new User();
//		user.setId("100");
//		list.add(user);
//		redisTemplate.opsForList().set
//		System.out.printf("-------------------------------------");
//		System.out.println(redisTemplate.opsForSet().members("userSet"));
	}

	@Test
	void f3(){
		String a  = "{\"autoStatus\":0,\"runningStatus\":0}";
		JSONObject jsonObject = JSON.parseObject(a);
//		redisTemplate.opsForList().leftPop("sceneList");
//		List sceneList = redisTemplate.opsForList().range("sceneList", 0, -1);
//		for (int i = 0; i < sceneList.size(); i++) {
//			redisTemplate.opsForList().remove("sceneList",i,sceneList.get(i));
//			redisTemplate.opsForList().leftPop("sceneList");
//		}



		List rsceneList = redisTemplate.opsForList().range("videoNodeList", 0, -1);
		System.out.println(rsceneList);
	}

}
