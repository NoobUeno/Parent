//package com.yao.demo;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.fastjson2.JSON;
//import com.example.generator.entity.Capture;
//import com.example.generator.entity.User;
//import org.springframework.mock.web.MockMultipartFile;
//import sun.misc.BASE64Decoder;
//
//import java.io.*;
//import java.util.*;
//
///**
// * @Entity: testController
// * @Author: RS
// * @Date: 2023/5/10
// * @Time: 16:02
// * @Description：<描述>
// **/
//public class test {
//
//    public static void main(String[] args) throws IOException {
//        new test().f2();
//    }
//
//    public void f1(){
//        List<User> list = new ArrayList<User>();
//        for (int i = 0; i <= 10; i++) {
//            User user = new User();
//            user.setId(String.valueOf(i));
//            user.setUsername("User"+i);
//            user.setPassword("wad"+i);
//            list.add(user);
//        }
//
//        String fileName = "/simpleWrite" + System.currentTimeMillis() + ".xlsx";
////        String fileName = "C:\\Users\\Ueno\\Desktop\\" + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        EasyExcel.write(fileName,User.class)
//                .sheet("模板")
//                .doWrite(() -> list);
//        Date date = new Date(1683475200000L);
//        Date date1 = new Date(1683561599000L);
//        System.out.println(date);
//        System.out.println(date1);
//    }
//
//
//    public void f2() throws IOException {
//
//        StringBuilder sb = new StringBuilder();
//
//        File file = new File("C:\\Users\\Ueno\\Desktop\\a.txt");
//        FileInputStream inputStream = new FileInputStream(file);
//
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        String string = null;
//
//        byte[] bytes = new byte[1024];
//
//        int len = 0;
//
//        while ((string = br.readLine()) != null){
//            sb.append(string);
//        }
//
//
//
//        Capture capture = JSON.parseObject(sb.toString(), Capture.class);
//
//
//        String imgStr = capture.getCaptureFaceBase64();
//        imgStr = imgStr.replaceAll("data:image/jpg;base64,","");
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] buffer = decoder.decodeBuffer(imgStr);
////        MockMultipartFile jpg = new MockMultipartFile(UUID.randomUUID() + ".jpg", buffer);
//
//        File out = new File("b.jpg");
//        FileOutputStream outputStream = new FileOutputStream(out);
//        BufferedOutputStream stream = new BufferedOutputStream(outputStream);
//        stream.write(buffer);
//
//    }
//
//    public String baseSwitch(String captureJsonString) throws IOException {
//
//        Capture capture = JSON.parseObject(captureJsonString, Capture.class);
//
//        String imgStr = capture.getCaptureFaceBase64();
//        imgStr = imgStr.replaceAll("data:image/jpg;base64,","");
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] buffer = decoder.decodeBuffer(imgStr);
//        MockMultipartFile jpg = new MockMultipartFile(UUID.randomUUID() + ".jpg", buffer);
//        return null;
//    }
//}
