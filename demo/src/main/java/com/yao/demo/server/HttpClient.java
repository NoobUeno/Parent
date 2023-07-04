package com.yao.demo.server;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.MultiValueMap;

/**
 * @Entity: HttpClient
 * @Author:
 * @Date: 2023/7/4
 * @Time: 15:13
 * @Description：<描述>
 **/
@Component
public class HttpClient {

    @Autowired
    private RestTemplate restTemplate;

    public void f1(){
        MultiValueMap<String,String> requestEntity = new LinkedMultiValueMap<>();
        String url = "http://localhost:8062/mq";
        restTemplate.getForObject(url,String.class,requestEntity);
    }

    public void SendPost(Object requestEntity,String url){
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(JSON.toJSONString(stringResponseEntity));
    }


}
