package com.yao.generator.mapper;

import com.yao.common.entity.Column;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ColumnMapper {

     List<Column> selectColumn(@Param("typeName") String typeName, @Param("schemaName") String schemaName);

}
