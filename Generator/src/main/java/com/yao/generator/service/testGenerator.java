package com.yao.generator.service;

import com.yao.generator.main;
import com.yao.generator.mapper.ColumnMapper;
import com.yao.common.entity.Column;
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

    public void start(Map<String,String> paramMap){
        try {
            String entityName = paramMap.get("entityName");
            String camelCaseEntityName = entityName.substring(0,1).toLowerCase()+entityName.substring(1);
            paramMap.put("camelCaseEntity",camelCaseEntityName);
            entity(paramMap);
            xml(paramMap);
            demo3(paramMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private ColumnMapper mapper;


    private String databaseName;

    private String xmlFtlPath;

    private String xmlFilePath;

    private String entityFtlPath;

    private String entityFilePath;

    private String entityQuote;

    private String daoFtlPath;

    private String daoFilePath;

    private String mapperQuote;

    @Value("${generator.dataBaseName}")
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    @Value("${generator.xml_ftlPath}")
    public void setXmlFtlPath(String xmlFtlPath) {
        this.xmlFtlPath = xmlFtlPath;
    }

    @Value("${generator.xml_filePath}")
    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    @Value("${generator.entity_ftlPath}")
    public void setEntityFtlPath(String entityFtlPath) {
        this.entityFtlPath = entityFtlPath;
    }

    @Value("${generator.entity_filePath}")
    public void setEntityFilePath(String entityFilePath) {
        this.entityFilePath = entityFilePath;
    }

    @Value("${generator.entity_quote}")
    public void setEntityQuote(String entityQuote) {
        this.entityQuote = entityQuote;
    }

    @Value("${generator.mapper_quote}")
    public void setMapperQuote(String mapperQuote) {
        this.mapperQuote = mapperQuote;
    }

    @Value("${generator.mapper_ftlPath}")
    public void setDaoFtlPath(String daoFtlPath) {
        this.daoFtlPath = daoFtlPath;
    }

    @Value("${generator.mapper_filePath}")
    public void setDaoFilePath(String daoFilePath) {
        this.daoFilePath = daoFilePath;
    }

    //包引用
    @Value("${generator.common_pagePrefix_quoted}")
    private String commonPagePrefix;


    /*
        实体类生成
         */
    public void entity(Map<String,String> paramMap) throws TemplateException, IOException {

        Map<String,String> typeMap = new HashMap<>();
        typeMap.put("bigint,varchar,text,longtext","String");
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
    public void xml(Map<String,String> mapperParamMap) throws TemplateException, IOException {
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
        typeMap.put("bigint,varchar,text,longtext","String");
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
                    sqlColumn.append(column.getColumn_name())
                            .append(",\n")
                            .append("\t\t");
                    break;
                }
            }

        }
        String sql;
        if (sqlColumn.lastIndexOf(",\n\t\t") == sqlColumn.length()-4){
            sql = sqlColumn.substring(0,sqlColumn.length()-4);
        }else sql = sqlColumn.toString();
        map.put("sqlColumn",sql);

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
        map.put("batchFirstHalfSql",strings.get(2));
        map.put("batchLowerHalfSql",strings.get(3));

        String updateSql = updateSqlGenerate(paramName, columns);
        map.put("updateSql",updateSql);

        map.put("idSql","id = #{id}");

        String filePath = xmlFilePath+entityName+"Mapper.xml";
        templateConfig(map,xmlFtlPath,filePath);
    }

    /*
        dao层接口生成
     */
    public void demo3(Map<String,String> mapperParamMap) throws TemplateException, IOException {
        Map<String,Object> map  = new HashMap<>();
        map.put("pagePrefix",commonPagePrefix);
        String entityName = mapperParamMap.get("entityName");
        String camelCaseEntity = mapperParamMap.get("camelCaseEntity");
        map.put("entityName",entityName);
        map.put("camelCaseEntity",camelCaseEntity);
        templateConfig(map,daoFtlPath,daoFilePath+entityName+"Mapper.java");
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
                        .append(" and ");
                        if(column.getColumn_name().contains("name") || column.getColumn_name().contains("Name")){
                            sb.append(column.getColumn_name()).append(" like concat('%', #{").append(paramName)
                                    .append(".").append(column.getColumn_name()).append("}")
                                    .append(",'%')");
                        }else {
                            sb.append(column.getColumn_name()).append(" = #{").append(paramName)
                                    .append(".").append(column.getColumn_name()).append("}");
                        }
                        sb
                        .append("\n")
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
            String columnName = column.getColumn_name();
            if(columnName.equals("id") ||columnName.equals("mtime") || columnName.equals("update_time")) continue;
            if (column.getColumn_type().equals("String")){
                firstHalfSql
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"")
                        .append(columnName).append(" != null and ")
                        .append(columnName)
                        .append(" != ''\">").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(columnName)
                        .append(",")
                        .append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("</if>")
                        .append("\n");
            }else {
                firstHalfSql
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t");
                if(columnName.equals("create_time") || columnName.equals("update_time")
                        || columnName.equals("atime") || columnName.equals("mtime")){
                    firstHalfSql
                            .append("\t")
                            .append(columnName)
                            .append(",");
                }else {
                    firstHalfSql
                            .append("<if test=\"")
                            .append(columnName).append(" != null\">")
                            .append("\n")
                            .append("\t")
                            .append("\t")
                            .append("\t")
                            .append("\t")
                            .append("\t")
                            .append(columnName).append(",")
                            .append("\n")
                            .append("\t")
                            .append("\t")
                            .append("\t")
                            .append("\t")
                            .append("</if>");
                }
                        firstHalfSql
                        .append("\n");
            }
        }

        StringBuilder lowerHalfSql = new StringBuilder();
        for (Column column: columns
        ) {
            String columnName = column.getColumn_name();
            if(columnName.equals("id") ||columnName.equals("mtime") || columnName.equals("update_time")) continue;
            if (columnName.equals("String")){
                lowerHalfSql
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(" #{")
                        .append(columnName)
                        .append("},")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\n");
            }else {
                lowerHalfSql.append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t");
                if(columnName.equals("create_time") || columnName.equals("update_time")
                        || columnName.equals("atime") || columnName.equals("mtime")){
                    lowerHalfSql
                            .append("now(),")
                            .append("\n");
                }else {
                    lowerHalfSql
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
                            .append("</if>");
                }
                lowerHalfSql
                        .append("\n");
            }
        }

        StringBuilder batchFirstHalfSql = new StringBuilder();
        for (Column column: columns
        ) {
            String columnName = column.getColumn_name();
            if(columnName.equals("id") ||columnName.equals("mtime") || columnName.equals("update_time")) continue;
            if (column.getColumn_type().equals("String")){
                batchFirstHalfSql
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(columnName)
                        .append(",")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\n");
            }else {
                batchFirstHalfSql
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t");
                if(columnName.equals("create_time")
                        || columnName.equals("atime")){
                    batchFirstHalfSql
                            .append("\t")
                            .append(columnName)
                            .append(",")
                            .append("\n");
                }else {
                    batchFirstHalfSql
                            .append(columnName).append(",")
                            .append("\t")
                            .append("\t")
                            .append("\t")
                            .append("\t")
                            .append("\n");
                }
            }
                            batchFirstHalfSql
                                .append("\n");
        }

        StringBuilder batchLowerHalfSql = new StringBuilder();
        for (Column column: columns
        ) {
            String columnName = column.getColumn_name();
            if(columnName.equals("id") ||columnName.equals("mtime") || columnName.equals("update_time")) continue;
            if (columnName.equals("String")){
                batchLowerHalfSql
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"")
                        .append(columnName).append(" != null and ")
                        .append(columnName)
                        .append(" != ''\">").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(" #{")
                        .append("item.")
                        .append(columnName).append("},").append("\n")

                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("</if>")
                        .append("\n");
            }else {
                batchLowerHalfSql.append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t");
                if(columnName.equals("create_time")
                        || columnName.equals("atime")){
                    batchLowerHalfSql
                            .append("now(),")
                            .append("\n");
                }else {
                    batchLowerHalfSql
                            .append(" #{")
                            .append(column.getColumn_name()).append("},").append("\n")
                            .append("\t")
                            .append("\t")
                            .append("\t")
                            .append("\t");
                }
                batchLowerHalfSql
                        .append("\n");
            }
        }

        List<String> list = new ArrayList<>();
        list.add(firstHalfSql.toString());
        list.add(lowerHalfSql.toString());
        list.add(batchFirstHalfSql.toString());
        list.add(batchLowerHalfSql.toString());
        return list;
    }

    public String updateSqlGenerate(String paramName,List<Column> columns){

        columns.remove("id");

        StringBuilder sb = new StringBuilder();
        for (Column column: columns
        ) {
            String columnName = column.getColumn_name();

            if(columnName.equals("id") || columnName.equals("atime") || columnName.equals("create_time")) continue;
            if (column.getColumn_type().equals("String")){
                sb
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("<if test=\"")
                        .append(columnName).append(" != null and ")
                        .append(columnName)
                        .append(" != ''\">").append("\n")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append("\t")
                        .append(columnName)
                        .append(" = #{")
                        .append(column.getColumn_name()).append("},")
                        .append("\n")
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
                        .append("\t");
                if (columnName.equals("mtime") || columnName.equals("update_time") ) {
                    sb
                            .append("\t")
                            .append(columnName)
                            .append(" = now(),");
                }else {
                    sb
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
                            .append(column.getColumn_name()).append("},")
                            .append("\n")
                            .append("\t")
                            .append("\t")
                            .append("\t")
                            .append("\t")
                            .append("</if>");
                }
                sb
                        .append("\n");
            }
        }

        return sb.toString();
    }

}
