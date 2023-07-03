package com.yao.rabbitmq.server;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Entity: Receiver
 * @Author:
 * @Date: 2023/7/3
 * @Time: 9:12
 * @Description：<描述>
 **/
@Component
@RabbitListener(queues = "faceContext")
public class Receiver {

    @RabbitHandler
    public void receiver(String msg){
        System.out.println(msg);
    }


}
