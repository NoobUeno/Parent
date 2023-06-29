package com.example.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @Entity: main
 * @Author:
 * @Date: 2023/6/25
 * @Time: 16:04
 * @Description：<描述>
 **/
public class main {
    public static void main(String[] args) throws IOException, TemplateException {

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("pageage", "com.example.test");

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding(Charset.forName("UTF-8").name());
        configuration.setClassForTemplateLoading(main.class, "/");
        Template template = configuration.getTemplate("/com/example/generator/ftl/demo1.ftl");
        FileOutputStream outputStream = new FileOutputStream(new File("a.txt"));

        template.process(objectMap, new OutputStreamWriter(outputStream, Charset.forName("UTF-8").name()));
        outputStream.close();
        System.out.println("文件创建成功");
    }
}
