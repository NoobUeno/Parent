package com.yao.websocket.tools;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Entity: WebSocketServer
 * @Author:
 * @Date: 2023/6/27
 * @Time: 18:11
 * @Description：<描述>
 **/
@ServerEndpoint(value = "/websocket")
@Component
@Slf4j
public class WebSocketServer {

    //记录在线人数
    private static int onlineCount = 0;

    //
    private static CopyOnWriteArraySet<WebSocketServer>webSocketServersSet = new CopyOnWriteArraySet<>();

    private Session session;

    public static synchronized  void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }

    public static synchronized  void subOnlineCount(){
        WebSocketServer.onlineCount--;
    }

    public static synchronized  int getOnlineCount(){
        return WebSocketServer.onlineCount;
    }


    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketServersSet.add(this);
        addOnlineCount();
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());

        try {
            sendMsg("连接成功");
        } catch (IOException e) {
            log.error("websocket io exception ");
        }
    }

    @OnClose
    public void onClose(){
        webSocketServersSet.remove(this);
        subOnlineCount();
        log.info("连接断开，当前在线人数为"+getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        log.info("收到信息："+message);
    }

    @OnError
    public void onError(Session session,Throwable error){
        log.error("发生错误");
        error.printStackTrace();
    }


    public void sendMsg(String message) throws IOException {
        System.out.println(message);
        for (WebSocketServer item: webSocketServersSet
             ) {
            item.session.getBasicRemote().sendText(message);
        }
    }


}
