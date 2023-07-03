package com.yao.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Entity: MqConfig
 * @Author:
 * @Date: 2023/7/3
 * @Time: 9:08
 * @Description：<描述>
 **/
@Configuration
public class MqConfig {

    public static final String QUEUE_NAME = "faceContext";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }
}
