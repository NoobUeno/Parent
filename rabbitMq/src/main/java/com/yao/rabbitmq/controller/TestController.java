package com.yao.rabbitmq.controller;

import com.yao.rabbitmq.server.CallbackSend;
import com.yao.rabbitmq.server.Send;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Entity: TestController
 * @Author:
 * @Date: 2023/7/3
 * @Time: 9:29
 * @Description：<描述>
 **/
@RestController
@RequestMapping("/mq")
public class TestController {

    @GetMapping
    public String f1(){
        String msg = "你好";
        String receiver = "faceContext";
        String callbackQueue = "callbackQueue";
        CorrelationData correlationData = new CorrelationData("1");
        CallbackSend.sendMsgByErrorExchange(msg,correlationData);
        CorrelationData correlationData2 = new CorrelationData("2");
        CallbackSend.sendMsgByErrorQueue(msg,correlationData2);
//        Send.sendMessage(msg,callbackQueue);
        return "success";

    }

}
