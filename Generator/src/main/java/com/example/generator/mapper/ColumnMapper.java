package com.example.generator.mapper;

import com.example.generator.entity.Column;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ColumnMapper {

     List<Column> selectColumn(@Param("typeName") String typeName, @Param("schemaName") String schemaName);

}
