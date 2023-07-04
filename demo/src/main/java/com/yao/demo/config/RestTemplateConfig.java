package com.yao.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Entity: RestTemplateConfig
 * @Author:
 * @Date: 2023/7/4
 * @Time: 14:34
 * @Description：<描述>
 **/
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);
        return new RestTemplate(factory);
    }

}
