package com.zsq.service;

import com.zsq.common.LayUIData;
import com.zsq.common.RetrunInfo;
import com.zsq.common.StudentScore;
import com.zsq.entity.Score;

public interface ScoreService {
    RetrunInfo updateScore(StudentScore studentScore);
    StudentScore getScore(String sid, String cid);
    LayUIData getScores(int page, int rows, String name);
    RetrunInfo deleteScoAll(Integer a[]);
    RetrunInfo addScore(Score score);
}
