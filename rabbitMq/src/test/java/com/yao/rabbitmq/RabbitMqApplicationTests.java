package com.yao.rabbitmq;

import com.yao.rabbitmq.server.CallbackSend;
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
        String msg = "{\"areaCode\":\"HD_123\",\"autoStatus\":0,\"deviceCode\":\"UPCUA-0001\",\"runningStatus\":0}";
        String receiver = "opcuaQueue";
        while(true){
            amqpTemplate.convertAndSend(receiver,msg);
        }
    }


    @Test
    void f2(){
        String msg = "{\"head\":\"Capture2\",\"alert_events\":[{\"area_id\":1,\"area_type\":\"polygon\",\"alertor_id\":1,\"alertor_type\":\"indicator_flag\",\"target\":{\"target_type\":\"indicator_flag\",\"target_id\":0,\"points\":[{\"x\":1000,\"y\":519},{\"x\":1064,\"y\":618}]}}],\"device_id\":\"M030201202111000909\",\"stream_id\":3,\"stream_name\":\"研发区域\",\"frame_id\":0,\"dts\":1687950795204,\"pts\":1687950795204,\"recv_ts\":1687950795204,\"filename_captureFace\":\"3962\",\"captureFace\":\"/profile/captureFace/20230628191318_captureFace.jpg\",\"filename_fullImage\":\"148972\",\"fullImage\":\"/profile/fullImage/20230628191318_fullImage.jpg\"}";
        String receiver = "topicQueue";
        amqpTemplate.convertAndSend(receiver,msg);
    }

    @Autowired
    private CallbackSend callbackSend;

    @Test
    void f3(){
        CallbackSend.sendMsg("234");

    }

}
