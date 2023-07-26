package com.example.generator;

import com.example.generator.mapper.ColumnMapper;
import com.example.generator.service.testGenerator;
import com.example.generator.entity.Column;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Entity: test
 * @Author:
 * @Date: 2023/6/25
 * @Time: 15:48
 * @Description：<描述>
 **/
@SpringBootTest(classes = GeneratorApplication.class)
public class test {

    @Autowired
    private testGenerator testGenerator;

    private String databaseName;

    @Value("${generator.dataBaseName}")
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    @Test
    public void f1() throws IOException, TemplateException {

        Map<String, Object> objectMap = new HashMap<>();
        //定义包路径
        objectMap.put("package", "com.example.test");

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding(Charset.forName("UTF-8").name());
        configuration.setClassForTemplateLoading(test.class, "/");
        Template template = configuration.getTemplate("/ftl/demo1.ftl");
        FileOutputStream outputStream = new FileOutputStream(new File("a.txt"));

        template.process(objectMap, new OutputStreamWriter(outputStream, Charset.forName("UTF-8").name()));
        outputStream.close();
        System.out.println("文件创建成功");

    }

    @Autowired
    private ColumnMapper mapper;

    @Test
    public void f2(){
        List<Column> columns = mapper.selectColumn("iot_node_info", "md_iot_safety");
        System.out.println(columns);
    }

    @Test
    public void f3() throws TemplateException, IOException {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("tableName","iot_face_node_info");
//        map.put("entityName","iFaceNode");
//        testGenerator.demo1(map);
    }

    @Test
    public void f4() throws TemplateException, IOException {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("entityName","NodeInfo");
            map.put("tableName","iot_node_info");
            testGenerator.entity(map);
            testGenerator.xml(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void f5(){
        String a = "UserName";
        String as = a.substring(0,1).toLowerCase()+a.substring(1);
        System.out.println(as);
    }

    @Test
    public void f6(){
        String s = "123123,";
        System.out.println(s.lastIndexOf(","));
        String substring = s.substring(0, s.length());
        System.out.println(substring);
    }

}
