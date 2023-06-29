package com.yao.demo.server;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @Entity: ServerMQTT
 * @Author: RS
 * @Date: 2023/5/18
 * @Time: 15:13
 * @Description：<描述>
 **/
public class ServerMQTT {

    //mqtt 监听端口 tcp
    public static final String Host = "tcp://127.0.0.1:1883";
    //指定的主题
    public static final String Topic = "pos_message_all";
    //定义mqtt的id
    private static final String ClientId = "server11";


    private MqttClient client;
    private MqttTopic mqttTopic;
    private MqttMessage message;

    public ServerMQTT() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client  = new MqttClient(Host,ClientId,new MemoryPersistence());
        connect();
    }

    private void connect(){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);

        //设置超时时间
        options.setConnectionTimeout(10);
        //设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect();
            mqttTopic =  client.getTopic(Topic);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

    }

    public void publish(MqttTopic topic,MqttMessage message) throws MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }

    public static void main(String[] args) throws MqttException {
        ServerMQTT server = new ServerMQTT();

        server.message = new MqttMessage();
        server.message.setQos(1);  //保证消息能到达一次
        server.message.setRetained(true);
        server.message.setPayload("这是推送消息的内容".getBytes());
        server.publish(server.mqttTopic , server.message);
        System.out.println(server.message.isRetained() + "------ratained状态");
    }

}
