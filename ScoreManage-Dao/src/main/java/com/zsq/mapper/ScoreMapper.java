package com.zsq.mapper;

import com.zsq.common.StudentScore;
import com.zsq.entity.Score;
import com.zsq.entity.ScoreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {
    int countByExample(ScoreExample example);

    int deleteByExample(ScoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    List<Score> selectByExample(ScoreExample example);

    Score selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Score record, @Param("example") ScoreExample example);

    int updateByExample(@Param("record") Score record, @Param("example") ScoreExample example);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
    List<StudentScore>  queryStudentScore();
    List<StudentScore>  selectByLike(String name);
    StudentScore queryBySCId(String sid, String cid);
   StudentScore selectStudentScoreById(Integer id);
}