package com.yao.websocket.controller;

import com.yao.websocket.tools.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Entity: testController
 * @Author:
 * @Date: 2023/6/27
 * @Time: 19:55
 * @Description：<描述>
 **/
@RestController
@RequestMapping("/test1")
public class testController {

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping
    public String f1() throws IOException {
        webSocketServer.sendMsg("8888");
    return "success";
    }

}
