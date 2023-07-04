package com.yao.rabbitmq;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;

/**
 * @Entity: MyRequest
 * @Author:
 * @Date: 2023/7/4
 * @Time: 16:28
 * @Description：<描述>
 **/
public class MyRequest extends RequestFacade {
    private Request request;

    public MyRequest(Request request) {
        super(request);
        this.request = request;
    }

    private Request getContext(){
        return request;
    }




}
