package com.zsq.mapper;

import com.zsq.entity.TeacherInfo;
import com.zsq.entity.TeacherInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherInfoMapper {
    int countByExample(TeacherInfoExample example);

    int deleteByExample(TeacherInfoExample example);

    int deleteByPrimaryKey(Integer teaId);

    int insert(TeacherInfo record);

    int insertSelective(TeacherInfo record);

    List<TeacherInfo> selectByExample(TeacherInfoExample example);

    TeacherInfo selectByPrimaryKey(Integer teaId);

    int updateByExampleSelective(@Param("record") TeacherInfo record, @Param("example") TeacherInfoExample example);

    int updateByExample(@Param("record") TeacherInfo record, @Param("example") TeacherInfoExample example);

    int updateByPrimaryKeySelective(TeacherInfo record);

    int updateByPrimaryKey(TeacherInfo record);
    List<TeacherInfo> selectAll();
    List<TeacherInfo> selectByLike(String teaName);
}