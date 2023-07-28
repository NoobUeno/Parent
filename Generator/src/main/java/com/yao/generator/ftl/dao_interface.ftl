package com.yao.generator.mapper;

import ${pagePrefix}.entity.${entityName};
import ${pagePrefix}.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ${entityName}Mapper {
    List<${entityName}> selectByConditions(@Param("${camelCaseEntity}") ${entityName} ${camelCaseEntity}, @Param("page") Page page);

    int insert(${entityName} ${camelCaseEntity});

    List<${entityName}> insertList(List<${entityName}> ${camelCaseEntity});

    int update(${entityName} ${camelCaseEntity});

    int delete(String id);

    }
