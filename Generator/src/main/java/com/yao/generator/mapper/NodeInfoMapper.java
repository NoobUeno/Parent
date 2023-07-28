package com.yao.generator.mapper;

import com.yao.common.entity.NodeInfo;
import com.yao.common.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NodeInfoMapper {
    List<NodeInfo> selectByConditions(@Param("nodeInfo") NodeInfo nodeInfo, @Param("page") Page page);

    int insert(NodeInfo nodeInfo);

    List<NodeInfo> insertList(List<NodeInfo> nodeInfo);

    int update(NodeInfo nodeInfo);

    int delete(String id);

    }
