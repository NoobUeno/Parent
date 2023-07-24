package com.yao.demo.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.apache.tomcat.jni.Time.sleep;

/**
 * @Entity: MyTimer
 * @Author:
 * @Date: 2023/7/6
 * @Time: 13:58
 * @Description：<描述>
 **/
@Component
public class MyTimer implements Runnable{

//    @Autowired
//    public RedisTemplate redisTemplate;


    private String key;

    private Integer second;

    private ScheduledExecutorService service = Executors.newScheduledThreadPool(100);


    public void setKey(String key) {
        this.key = key;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }



    @Override
    public void run() {
        System.out.println("定时器启动！");
        task2();
        service.shutdown();
    }

    private boolean task(){
//        Object o = redisTemplate.opsForValue().get(key);
//        if (o != null){
//            return true;
//        }else return false;

        try {
            Thread.sleep(second * 1000);
            System.out.println("key : "+key+",second : "+second);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    return false;
    }

    private boolean task2(){

        service.schedule(() ->{

        },second, TimeUnit.SECONDS);
        System.out.println("hello");
        return false;
    }

}
