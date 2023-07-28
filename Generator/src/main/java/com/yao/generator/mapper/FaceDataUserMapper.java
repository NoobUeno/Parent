package com.yao.generator.mapper;

import com.yao.common.entity.FaceDataUser;
import com.yao.common.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FaceDataUserMapper {
    List<FaceDataUser> selectByConditions(@Param("faceDataUser") FaceDataUser faceDataUser, @Param("page") Page page);

    int insert(FaceDataUser faceDataUser);

    List<FaceDataUser> insertList(List<FaceDataUser> faceDataUser);

    int update(FaceDataUser faceDataUser);

    int delete(String id);

    }
