package com.yao.rabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class RabbitMqApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void f1(){
        String msg = "你好";
        String receiver = "faceContext";
        amqpTemplate.convertAndSend(receiver,msg);
    }

}
