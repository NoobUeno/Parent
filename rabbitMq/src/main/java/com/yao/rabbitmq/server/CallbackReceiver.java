package com.yao.rabbitmq.server;

import com.alibaba.fastjson2.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Entity: CallbackReceiver
 * @Author:
 * @Date: 2023/7/4
 * @Time: 10:16
 * @Description：<描述>
 **/
@Service
public class CallbackReceiver {

    @RabbitListener(queues = "callbackQueue")
    public void receiverMsg(Message message){
        String a  = new String(message.getBody());
        System.out.println(a);
        System.out.println("-------------------------------------------------");
        System.out.println(JSON.toJSONString(message));
    }

}
