package com.yao.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.yao.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @Entity: testController
 * @Author: RS
 * @Date: 2023/5/11
 * @Time: 14:10
 * @Description：<描述>
 **/
@RestController
@RequestMapping("/test")
public class testController {
    @GetMapping
    public void test1(HttpServletResponse response){
        System.out.println(this.getClass().getResource("/"));

        List<User> list = new ArrayList<User>();
        for (int i = 0; i <= 10; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setUsername("User"+i);
            user.setPassword("wad"+i);
            list.add(user);
        }

        String filePath = this.getClass().getResource("/")+"simpleWrite" + System.currentTimeMillis() + ".xlsx";
        filePath = filePath.substring(filePath.indexOf("C:"));
        System.out.println(filePath);
        EasyExcel.write(filePath,User.class)
                .sheet("列表数据")
                .doWrite(() -> list);
        
        File file = new File(filePath);
        String filename = file.getName();
        System.out.println(filename);
        if (file.exists()) {
            try (
                    FileInputStream inputStream = new FileInputStream(file);
            ) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream;charset=utf-8");
                response.setHeader("Content-disposition","attachment;filename="+filename);
                byte[] a = new byte[1024];
                int b;
                while ((b = inputStream.read(a)) != -1){
                    response.getOutputStream().write(a,0,b);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void exportExcel(List resultList,HttpServletResponse response,Class clazz,String fileName){
        String filePath = this.getClass().getResource("/")+fileName+"-"+new Date(System.currentTimeMillis()) + ".xlsx";
        filePath = filePath.substring(filePath.indexOf("C:"));
        EasyExcel.write(filePath,clazz)
                .sheet("列表数据")
                .doWrite(() -> resultList);

        File file = new File(filePath);
        String filename = file.getName();
        System.out.println(filename);
        if (file.exists()) {
            try (
                    FileInputStream inputStream = new FileInputStream(file);
            ) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/octet-stream;charset=utf-8");
                response.setHeader("Content-disposition","attachment;filename="+filename);
                byte[] a = new byte[1024];
                int b;
                while ((b = inputStream.read(a)) != -1){
                    response.getOutputStream().write(a,0,b);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

