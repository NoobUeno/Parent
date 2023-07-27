package ${pagePrefix}.mapper;

import ${pagePrefix}.entity.FaceNode;
import ${pagePrefix}.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FaceNodeMapper {
    List<${entityName}> selectByConditions(@Param("${entityName}") ${camelCaseEntity}, @Param("page") Page page);

    int insert(${entityName} ${entityName});

    int update(${entityName} ${entityName});

    int delete(String id);

    }
