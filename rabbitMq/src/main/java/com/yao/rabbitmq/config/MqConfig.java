package com.yao.rabbitmq.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

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

    private static String host;

    private static String port;

    private static String username;

    private static String password;

    @Value("${spring.rabbitmq.host}")
    public void setHost(String host) {
        this.host = host;
    }
    @Value("${spring.rabbitmq.port}")
    public void setPort(String port) {
        this.port = port;
    }
    @Value("${spring.rabbitmq.username}")
    public void setUsername(String username) {
        this.username = username;
    }
    @Value("${spring.rabbitmq.password}")
    public void setPassword(String password) {
        this.password = password;
    }

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

    @Bean
    public Channel channel(){
        Channel channel = null;
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(host);
            connectionFactory.setPort(Integer.parseInt(port));
            connectionFactory.setPassword(password);
            connectionFactory.setUsername(username);
            Connection connection = connectionFactory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        return channel;
    }

}
