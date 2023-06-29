package com.yao.demo;

import com.alibaba.excel.EasyExcel;
import com.yao.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Entity: testController
 * @Author: RS
 * @Date: 2023/5/10
 * @Time: 16:02
 * @Description：<描述>
 **/
public class test {

    public static void main(String[]Args){
        List<User> list = new ArrayList<User>();
        for (int i = 0; i <= 10; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setUsername("User"+i);
            user.setPassword("wad"+i);
            list.add(user);
        }

        String fileName = "/simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        String fileName = "C:\\Users\\Ueno\\Desktop\\" + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName,User.class)
                .sheet("模板")
                .doWrite(() -> list);
        Date date = new Date(1683475200000L);
        Date date1 = new Date(1683561599000L);
        System.out.println(date);
        System.out.println(date1);
    }

}
