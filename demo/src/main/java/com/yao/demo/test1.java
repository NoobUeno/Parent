package com.yao.demo;

import com.yao.demo.server.MyTimer;

/**
 * @Entity: test1
 * @Author:
 * @Date: 2023/7/6
 * @Time: 14:45
 * @Description：<描述>
 **/
public class test1 {
    public static void main(String[] args) {
        new test1().Scheduled();
//        new test1().ThreadSleep();
    }

    public void ThreadSleep(){
        MyTimer timer = new MyTimer();
        timer.setKey("123");
        timer.setSecond(5);
        Thread thread = new Thread(timer);
        thread.start();

        MyTimer timer1 = new MyTimer();
        timer1.setKey("123");
        timer1.setSecond(5);
        new Thread(timer1).start();
    }

    public void Scheduled(){
        MyTimer myTimer = new MyTimer();
        myTimer.setSecond(3);
        myTimer.setKey("123");
        new Thread(myTimer).start();
    }
}
