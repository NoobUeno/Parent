package com.yao.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

//    public static final String QUEUE_NAME = "faceContext";
//
//    @Bean
//    public Queue queue(){
//        return new Queue(QUEUE_NAME);
//    }

    public static final String CALLBACKQUEUE = "callbackQueue";

    public static final String CALLBACKEXCHANGE = "callbackExchange";

    @Bean
    public Queue callbackQueue(){
        return new Queue(CALLBACKQUEUE);
    }

    @Bean
    public TopicExchange callbackExchange(){
        return new TopicExchange(CALLBACKEXCHANGE);
    }

    @Bean
    public Binding callbackbinding(){
        return BindingBuilder.bind(callbackQueue()).to(callbackExchange()).with("callback");
    }

}
