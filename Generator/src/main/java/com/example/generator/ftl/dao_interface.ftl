package ${pagePrefix}.mapper;

import ${pagePrefix}.entity.FaceNode;
import ${pagePrefix}.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FaceNodeMapper {
    List<${entityName}> selectByConditions(@Param("faceNode") ${entityName} ${}, @Param("page") Page page);

    int add(FaceNode faceNode);

    int update(FaceNode faceNode);

    int delete(String id);

    }
