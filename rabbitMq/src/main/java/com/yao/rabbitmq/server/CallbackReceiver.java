package com.yao.rabbitmq.server;

import com.alibaba.fastjson2.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
    public void receiverMsg(Message message,Channel channel) throws IOException {
        String a  = new String(message.getBody());
        System.out.println(a);
        System.out.println("-------------------------------------------------");
        System.out.println(JSON.toJSONString(message));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        //手动应答
//        channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
//        try {
//            if(a.equals("234")){
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

}
