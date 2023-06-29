package com.example.generator.service;

import com.example.generator.main;
import com.example.generator.mapper.ColumnMapper;
import com.yao.entity.Column;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Entity: testGenerator
 * @Author: RS
 * @Date: 2023/6/25
 * @Time: 15:27
 * @Description：<描述>
 **/
@Component
public class testGenerator {

    public void templateConfig(Map<String,Object> list,String ftlPath,String filePath) throws IOException, TemplateException {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding(Charset.forName("UTF-8").name());
        configuration.setClassForTemplateLoading(main.class, "/");
        Template template = configuration.getTemplate(ftlPath);
        FileOutputStream outputStream = new FileOutputStream(filePath);

        template.process(list, new OutputStreamWriter(outputStream, Charset.forName("UTF-8").name()));
        outputStream.close();
        System.out.println("文件创建成功");
    }


    @Autowired
    private ColumnMapper mapper;


    private String databaseName;

    @Value("${generator.dataBaseName}")
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    private String xmlFtlPath = "";

    @Value("${generator.xmlFtlPath}")
    public void setXmlFtlPath(String xmlFtlPath) {
        this.xmlFtlPath = xmlFtlPath;
    }

    private String xmlFilePath = "";

    @Value("${generator.xmlFilePath}")
    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    private String entityFtlPath = "";

    @Value("${generator.entityFtlPath}")
    public void setEntityFtlPath(String entityFtlPath) {
        this.entityFtlPath = entityFtlPath;
    }

    private String entityFilePath = "";

    @Value("${generator.entityFilePath}")
    public void setEntityFilePath(String entityFilePath) {
        this.entityFilePath = entityFilePath;
    }

    private String entityQuote;

    @Value("${generator.entityQuote}")
    public void setEntityQuote(String entityQuote) {
        this.entityQuote = entityQuote;
    }

    private String mapperQuote;

    @Value("${generator.mapperQuote}")
    public void setMapperQuote(String mapperQuote) {
        this.mapperQuote = mapperQuote;
    }

    /*
        实体类生成
         */
    public void demo1(Map<String,String> paramMap) throws TemplateException, IOException {

        Map<String,String> typeMap = new HashMap<>();
        typeMap.put("bigint,varchar,text","String");
        typeMap.put("int,integer","Integer");
        typeMap.put("double","Double");
        typeMap.put("timestamp,datetime,date,time","Date");


        Map<String,Object> map = new HashMap<>();
        List<Column> oldColumns = mapper.selectColumn(paramMap.get("tableName"), databaseName);
        List<Column> columns = new ArrayList<>();
                for (Column column : oldColumns
             ) {
            String columnType = column.getColumn_type();
            if (columnType.contains("(")) columnType = columnType.substring(0,columnType.indexOf('('));
            Set<String> strings = typeMap.keySet();
            for (String key: strings
                 ) {
                if (key.contains(columnType)){
                    Column property = new Column();
                    property.setColumn_name(column.getColumn_name());
                    property.setColumn_type(typeMap.get(key));
                    columns.add(property);
                    break;
                }
            }
        }
        String entityName = paramMap.get("entityName");

        map.put("columns",columns);
        map.put("entityName",entityName);
        templateConfig(map,entityFtlPath,entityFilePath+entityName+".java");
    }

    /*
        mybatis映射文件生成
     */
    public void demo2(Map<String,String> mapperParamMap) throws TemplateException, IOException {
        Map<String,Object> map = new HashMap<>();

        String entityName = mapperParamMap.get("entityName");
        String paramName = entityName.toLowerCase();
        String mapperName = entityName+"Mapper";
        map.put("mapperName",mapperName);
        map.put("entityName",mapperParamMap.get("entityName"));
        map.put("tableName",mapperParamMap.get("tableName"));
        map.put("paramName",paramName);
        map.put("entityQuote",entityQuote);
        map.put("mapperQuote",mapperQuote);

        Map<String,String> typeMap = new HashMap<>();
        typeMap.put("bigint,varchar,text","String");
        typeMap.put("int,integer","Integer");
        typeMap.put("double","Double");
        typeMap.put("timestamp,datetime,date,time","Date");



        List<Column> oldColumns = mapper.selectColumn(mapperParamMap.get("tableName"), databaseName);
        List<Column> columns = new ArrayList<>();

        StringBuilder sqlColumn = new StringBuilder();

        for (Column column : oldColumns
        ) {
            String columnType = column.getColumn_type();
            if (columnType.contains("(")) columnType = columnType.substring(0,columnType.indexOf('('));
            Set<String> strings = typeMap.keySet();
            for (String key: strings
            ) {
                if (key.contains(columnType)){
                    Column property = new Column();
                    property.setColumn_name(column.getColumn_name());
                    property.setColumn_type(typeMap.get(key));
                    columns.add(property);
                    sqlColumn.append(column.getColumn_name()).append(",\n").append("\t\t");
                    break;
                }
            }
        }
        map.put("sqlColumn",sqlColumn.toString());


        String selectSql = selectSqLGenerate(paramName, columns);
        map.put("selectDynamicSql",selectSql);
        map.put("pageSql","<if test=\"page != null\">\n" +
                "            <if test=\"page.pageSize != null and page.pageNum != null\">\n" +
                "                limit #{page.pageNum},#{page.pageSize}\n" +
                "            </if>\n" +
                "        </if>");

        List<String> strings = insertStringGenerate(paramName, columns);
        map.put("firstHalfSql",strings.get(0));
        map.put("lowerHalfSql",strings.get(1));

        String updateSql = updateSqlGenerate(paramName, columns);
        map.put("updateSql",updateSql);

        map.put("idSql","id = #{id}");


        String filePath = xmlFilePath+entityName+"Mapper.xml";


        templateConfig(map,xmlFtlPath,filePath);
    }

    public String selectSqLGenerate(String paramName, List<Column> columns){
        StringBuilder sb = new StringBuilder();
        for (Column column: columns
        ) {
            if (column.getColumn_type().equals("String")){
                sb
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"").append(paramName).append(".")
                        .append(column.getColumn_name()).append(" != null and ")
                        .append(paramName).append(".").append(column.getColumn_name())
                        .append(" != ''\">").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(" and ")
                        .append(column.getColumn_name()).append(" = #{").append(paramName)
                        .append(".").append(column.getColumn_name()).append("}").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("</if>")
                        .append("\n");
            }else {
                sb.append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"").append(paramName).append(".")
                        .append(column.getColumn_name()).append(" != null\">")
                        .append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(" and ").append(column.getColumn_name()).append(" = #{").append(paramName)
                        .append(".").append(column.getColumn_name()).append("}").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("</if>")
                        .append("\n");
            }
        }
        return sb.toString();
    }

    public List<String> insertStringGenerate(String paramName, List<Column> columns){



        StringBuilder firstHalfSql = new StringBuilder();
        for (Column column: columns
        ) {
            if (column.getColumn_type().equals("String")){
                firstHalfSql
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"")
                        .append(column.getColumn_name()).append(" != null and ")
                        .append(column.getColumn_name())
                        .append(" != ''\">").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(column.getColumn_name())
                        .append(",")
                        .append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("</if>")
                        .append("\n");
            }else {
                firstHalfSql.append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"")
                        .append(column.getColumn_name()).append(" != null\">")
                        .append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(column.getColumn_name()).append(",")
                        .append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("</if>")
                        .append("\n");
            }
        }

        StringBuilder lowerHalfSql = new StringBuilder();
        for (Column column: columns
        ) {
            if (column.getColumn_type().equals("String")){
                lowerHalfSql
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"")
                        .append(column.getColumn_name()).append(" != null and ")
                        .append(column.getColumn_name())
                        .append(" != ''\">").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(" #{")
                        .append(column.getColumn_name()).append("},").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("</if>")
                        .append("\n");
            }else {
                lowerHalfSql.append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"")
                        .append(column.getColumn_name()).append(" != null\">")
                        .append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(" #{")
                        .append(column.getColumn_name()).append("},").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("</if>")
                        .append("\n");
            }
        }

        List<String> list = new ArrayList<>();
        list.add(firstHalfSql.toString());
        list.add(lowerHalfSql.toString());
        return list;
    }

    public String updateSqlGenerate(String paramName,List<Column> columns){

        columns.remove("id");

        StringBuilder sb = new StringBuilder();
        for (Column column: columns
        ) {
            if (column.getColumn_type().equals("String")){
                sb
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"")
                        .append(column.getColumn_name()).append(" != null and ")
                        .append(column.getColumn_name())
                        .append(" != ''\">").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(column.getColumn_name())
                        .append(" = #{")
                        .append(column.getColumn_name()).append("},").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("</if>")
                        .append("\n");
            }else {
                sb.append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"")
                        .append(column.getColumn_name()).append(" != null\">")
                        .append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(column.getColumn_name())
                        .append(" = #{")
                        .append(column.getColumn_name()).append("},").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("</if>")
                        .append("\n");
            }
        }

        return sb.toString();
    }

}
