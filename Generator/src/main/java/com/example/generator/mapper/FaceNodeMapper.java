package com.example.generator.mapper;

import com.yao.entity.FaceNode;
import com.yao.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FaceNodeMapper {
    List<FaceNode> selectByConditions(@Param("faceNode") FaceNode faceNode,@Param("page") Page page);

    int add(FaceNode faceNode);

    int update(FaceNode faceNode);

    int delete(String id);

}
