package com.yao.rabbitmq.server;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Entity: Send
 * @Author:
 * @Date: 2023/7/3
 * @Time: 9:19
 * @Description：<描述>
 **/
@Component
public class Send {

    private static AmqpTemplate amqpTemplate;

    @Autowired
    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public static void sendMessage(String msg, String queueName){
        amqpTemplate.convertAndSend(queueName,msg);
    }

}
