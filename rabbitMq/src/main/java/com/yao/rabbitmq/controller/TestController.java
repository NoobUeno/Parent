package com.yao.rabbitmq.controller;

import com.alibaba.fastjson2.JSON;
import com.yao.rabbitmq.MyRequest;
import com.yao.rabbitmq.server.CallbackSend;
import com.yao.rabbitmq.server.Send;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping
    public Map f2(HttpServletRequest request,RequestFacade requestFacade){
        try {
            Field field = requestFacade.getClass().getDeclaredField("request");
            field.setAccessible(true);
            Request request1 = (Request) field.get(request);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println(1/0);
        System.out.println("123123");
        Map<String,String> map = new HashMap<>();
        map.put("1234","1234");
        return map;
    }

}
