package com.yao.rabbitmq.server;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Entity: CallbackSend
 * @Author:
 * @Date: 2023/7/4
 * @Time: 10:13
 * @Description：<描述>
 **/
@Service
public class CallbackSend {


    private static RabbitTemplate rabbitTemplate;

    static String exchange = "callbackExchange";

    static String routingKey = "callback";

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public static void sendMsg(String msg){
        rabbitTemplate.convertAndSend(exchange,routingKey,msg);
    }

    public static void sendMsgByErrorExchange(String msg, CorrelationData correlationData){
        rabbitTemplate.convertAndSend(exchange+"123",routingKey,msg,correlationData);
    }

    public static void sendMsgByErrorQueue(String msg,CorrelationData correlationData){
        rabbitTemplate.convertAndSend(exchange,routingKey+"123",msg,correlationData);
    }

}
