package com.yao.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Entity: MQCallback
 * @Author:
 * @Date: 2023/7/4
 * @Time: 11:18
 * @Description：<描述>
 **/
@Component
public class MQCallback implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     *
     * @param correlationData 存储消息id和自定义信息
     * @param flag 是否成功发送
     * @param s 失败的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean flag, String s) {
        String id = correlationData != null ? correlationData.getId() : "空";
        if (flag) {
            System.out.println("交换机接收到了消息，id:"+id);
        }else {
            System.out.println("交换机没有收到id："+id+",的消息");
            System.out.println("原因是："+s);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("发送到队列失败！");
        System.out.println("正文"+message);
        System.out.println(i);
        System.out.println("错误原因"+s);
        System.out.println("交换机："+s1);
        System.out.println("路由："+s2);
    }
}
